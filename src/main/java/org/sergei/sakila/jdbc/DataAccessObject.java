package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
import org.sergei.sakila.model.FieldType;
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
public class DataAccessObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessObject.class);

    private final DataSource dataSource;

    @Autowired
    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Address getAddressWithMetadata(long cityId, String postalCode) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        final String metaDataSql =
                "SELECT " +
                        "ma.ui_description, " +
                        "ma.fld_type " +
                        "FROM " +
                        "md_addr ma " +
                        "WHERE " +
                        "ma.city_id = :cityId";
        MapSqlParameterSource metaDataParams = new MapSqlParameterSource()
                .addValue("cityId", cityId);

        List<AddressMetaData> addressMetaDataList = new LinkedList<>();
        AddressMetaData addressMetaData = jdbc.queryForObject(
                metaDataSql, metaDataParams, (rs, rowNum) ->
                        new AddressMetaData.AddressMetaDataBuilder()
                                .withUiDescription(rs.getString("ui_description"))
                                .withFieldType(FieldType.valueOf(rs.getString("fld_type")))
                                .build()
        );
        addressMetaDataList.add(addressMetaData);
        LOGGER.debug("Field type: {} and UI description: {}",
                Objects.requireNonNull(addressMetaData).getFieldType(),
                Objects.requireNonNull(addressMetaData).getUiDescription());

        final String addressSql =
                "SELECT " +
                        "c.city, " +
                        "a.address, " +
                        "a.address2, " +
                        "a.district, " +
                        "a.postal_code " +
                        "FROM " +
                        "address a " +
                        "INNER JOIN " +
                        "city c ON c.city_id = :cityId " +
                        "AND a.postal_code = :postalCode";
        MapSqlParameterSource objectParams = new MapSqlParameterSource()
                .addValue("cityId", cityId)
                .addValue("postalCode", postalCode);

        Address address = jdbc.queryForObject(
                addressSql, objectParams, (rs, rowNum) ->
                        new Address.AddressBuilder()
                                .withFirstAddress(rs.getString("address"))
                                .withSecondAddress(rs.getString("address2"))
                                .withDistrict(rs.getString("district"))
                                .withPostalCode(rs.getString("postal_code"))
                                .withAddressMetadata(addressMetaDataList)
                                .build()
        );

        LOGGER.debug("2nd SQL: {} executed with this params: {}", addressSql, objectParams.getValues());

        return address;
    }
}
