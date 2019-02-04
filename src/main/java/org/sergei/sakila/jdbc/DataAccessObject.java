package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Sergei Visotsky
 */
@Repository
public class DataAccessObject implements IDataAccessObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessObject.class);

    private final DataSource dataSource;

    @Autowired
    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<PaymentFormData> getPaymentFromDataAndMetaData(long customerId) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
        final String paymentFormMetaDataSql =
                "SELECT " +
                        "    mp.ui_description, " +
                        "    mp.field_type, " +
                        "    mp.lang_type " +
                        "FROM " +
                        "    sakila.md_payment mp " +
                        "WHERE " +
                        "    mp.customer_id = :customerId";
        MapSqlParameterSource params = new MapSqlParameterSource("customerId", customerId);

        List<PaymentFormMetaData> paymentFormMetaDataList = new LinkedList<>();
        PaymentFormMetaData paymentFormMetaData = jdbc.queryForObject(
                paymentFormMetaDataSql, params,
                (rs, rowNum) ->
                        new PaymentFormMetaData.PaymentFormMetaDataBuilder()
                                .withUiDescription(rs.getString("ui_description"))
                                .withFieldType(FieldType.valueOf(rs.getString("field_type")))
                                .withLanguageType(LanguageType.valueOf(rs.getString("lang_type")))
                                .build()
        );
        paymentFormMetaDataList.add(paymentFormMetaData);

        final String paymentFormDataSql =
                "SELECT " +
                        "    c.first_name, " +
                        "    c.last_name, " +
                        "    c.email, " +
                        "    c.create_date, " +
                        "    c.last_update, " +
                        "    p.rental_id, " +
                        "    p.amount " +
                        "FROM " +
                        "    customer c " +
                        "        INNER JOIN " +
                        "    payment p ON c.customer_id = :customerId";

        // FIXME: HTTP: 500 - No message available
        return jdbc.query(paymentFormDataSql, params,
                (rs, rowNum) ->
                        new PaymentFormData.PaymentFormDataBuilder()
                                .withFirstName(rs.getString("first_name"))
                                .withLastName(rs.getString("last_name"))
                                .withEmail(rs.getString("email"))
                                .withCreateDate(rs.getDate("create_date"))
                                .withLastUpdate(rs.getDate("last_update"))
                                .withRentalId(rs.getLong("rental_id"))
                                .withAmount(rs.getInt("amount"))
                                .build());
    }

    @Override
    public Address getAddressWithMetadata(long cityId, long addressId) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        final String addressMetaDataSql =
                "SELECT " +
                        "    ma.ui_description, " +
                        "    ma.field_type " +
                        "FROM " +
                        "    md_addr ma " +
                        "WHERE " +
                        "    ma.city_id = :cityId";
        MapSqlParameterSource metaDataParams = new MapSqlParameterSource()
                .addValue("cityId", cityId);

        List<AddressMetaData> addressMetaDataList = new LinkedList<>();
        AddressMetaData addressMetaData = jdbc.queryForObject(
                addressMetaDataSql, metaDataParams, (rs, rowNum) ->
                        new AddressMetaData.AddressMetaDataBuilder()
                                .withUiDescription(rs.getString("ui_description"))
                                .withFieldType(FieldType.valueOf(rs.getString("field_type")))
                                .build()
        );
        addressMetaDataList.add(addressMetaData);
        LOGGER.debug("Field type: {} and UI description: {}",
                Objects.requireNonNull(addressMetaData).getFieldType(),
                Objects.requireNonNull(addressMetaData).getUiDescription());

        final String addressSql =
                "SELECT " +
                        "    c.first_name, " +
                        "    c.last_name, " +
                        "    c.email, " +
                        "    a.address, " +
                        "    a.district, " +
                        "    a.city_id, " +
                        "    ci.city " +
                        "FROM " +
                        "    customer c " +
                        "        INNER JOIN " +
                        "    address a ON c.address_id = a.address_id " +
                        "        INNER JOIN " +
                        "    city ci ON ci.city_id = a.address_id " +
                        "WHERE " +
                        "    a.city_id = :cityId AND c.address_id = :addressId";
        MapSqlParameterSource objectParams = new MapSqlParameterSource()
                .addValue("cityId", cityId)
                .addValue("addressId", addressId);

        Address address = jdbc.queryForObject(
                addressSql, objectParams,
                (rs, rowNum) ->
                        new Address.AddressBuilder()
                                .withFirstName(rs.getString("first_name"))
                                .withLastName(rs.getString("last_name"))
                                .withEmail(rs.getString("email"))
                                .withAddress(rs.getString("address"))
                                .withDistrict(rs.getString("district"))
                                .withCityId(rs.getLong("city_id"))
                                .withCity(rs.getString("city"))
                                .withAddressMetadata(addressMetaDataList)
                                .build()
        );
        LOGGER.debug("Address data taken from DB: {}", Objects.requireNonNull(address).toString());
        LOGGER.debug("2nd SQL: {} executed with this params: {}", addressSql, objectParams.getValues());

        return address;
    }
}
