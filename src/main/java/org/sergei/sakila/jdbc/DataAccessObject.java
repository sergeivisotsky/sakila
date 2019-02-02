package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
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

    private final DataSource dataSource;

    @Autowired
    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Address getAddressWithMetadata(long cityId, String postalCode) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        String metaDataSQL = "SELECT " +
                "ma.ui_description, ma.fld_type " +
                "FROM " +
                "md_addr ma " +
                "WHERE " +
                "ma.city_id = :cityId";

        MapSqlParameterSource objectParams = new MapSqlParameterSource();
        objectParams.addValue("cityId", cityId);
//        objectParams.addValue("postalCode", postalCode);

        List<AddressMetaData> addressMetadata = jdbc.queryForList(metaDataSQL, objectParams, AddressMetaData.class);

        String addressSQL =
                "SELECT " +
                        "c.city, a.address, a.address2, a.district, a.postal_code " +
                        "FROM " +
                        "address a " +
                        "INNER JOIN " +
                        "city c ON c.city_id = :cityId " +
                        "AND a.postal_code = :postalCode";

        MapSqlParameterSource metaDataParams = new MapSqlParameterSource();
        metaDataParams.addValue("cityId", cityId);
        metaDataParams.addValue("postalCode", postalCode);
//        RowMapper<Address>
        Address address = jdbc.queryForObject(addressSQL, metaDataParams, Address.class);


        return address;
    }
}
