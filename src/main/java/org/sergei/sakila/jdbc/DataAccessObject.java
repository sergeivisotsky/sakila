package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Repository
public class DataAccessObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessObject.class);

    private static final String META_DATA_SQL = "SELECT " +
            "ma.ui_description, ma.fld_type " +
            "FROM " +
            "md_addr ma " +
            "WHERE " +
            "ma.city_id = :cityId";
    private static final String ADDRESS_SQL = "SELECT " +
            "c.city, a.address, a.address2, a.district, a.postal_code " +
            "FROM " +
            "address a " +
            "INNER JOIN " +
            "city c ON c.city_id = :cityId " +
            "AND a.postal_code = :postalCode";

    private final DataSource dataSource;

    @Autowired
    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Address getAddressWithMetadata(long cityId, String postalCode) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource metaDataParams = new MapSqlParameterSource();
        metaDataParams.addValue("cityId", cityId);

        List<AddressMetaData> addressMetadata = jdbc.queryForList(META_DATA_SQL, metaDataParams, AddressMetaData.class);

        LOGGER.debug("1st SQL: {} executed with this params {}", META_DATA_SQL, metaDataParams);

        MapSqlParameterSource objectParams = new MapSqlParameterSource();
        objectParams.addValue("cityId", cityId);
        objectParams.addValue("postalCode", postalCode);

        Address address = jdbc.queryForObject(ADDRESS_SQL, objectParams, (rs, rowNum) ->
                new Address.AddressBuilder()
                        .withFirstAddress(rs.getString("address"))
                        .withSecondAddress(rs.getString("address2"))
                        .withDistrict(rs.getString("district"))
                        .withPostalCode(rs.getString("postal_code"))
                        .withAddressMetadata(addressMetadata)
                        .build());

        LOGGER.debug("2nd SQL: {} executed with this params {}", ADDRESS_SQL, objectParams);

        return address;
    }
}
