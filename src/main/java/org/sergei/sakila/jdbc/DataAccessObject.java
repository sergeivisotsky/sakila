package org.sergei.sakila.jdbc;

import com.google.common.collect.ImmutableMap;
import org.sergei.sakila.Loggers;
import org.sergei.sakila.model.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
public class DataAccessObject implements IDataAccessObject {

    private final DataSource dataSource;

    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public FormMetaData getFormMetaData(long formId, String langType) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
        ImmutableMap<String, Object> paramsFormType = ImmutableMap.of("formId", formId);
        final String formTypeSql =
                "SELECT \n" +
                        "    ft.num_of_elem, ft.frm_descr\n" +
                        "FROM\n" +
                        "    md_frm_type ft\n" +
                        "WHERE\n" +
                        "    ft.md_frm_id = :formId";
        Loggers.sql(formTypeSql, paramsFormType);
        String[] formDescription = new String[1];
        FormType formType = jdbc.query(formTypeSql, paramsFormType,
                rs -> {
                    if (rs.next()) {
                        formDescription[0] = rs.getString("frm_descr");
                    }
                    return new FormType.FormTypeBuilder()
                            .withNumberOfElements(rs.getInt("num_of_elem"))
                            .withFormDescription(rs.getString("frm_descr"))
                            .build();
                }
        );

        ImmutableMap<String, Object> formMetaDataParams = ImmutableMap.of("formId", formId, "langType", langType);

        final String formMetaDataSql =
                "SELECT \n" +
                        "    f.ui_description,\n" +
                        "    f.elem_number,\n" +
                        "    f.field_type,\n" +
                        "    t.lang_type,\n" +
                        "    t.text\n" +
                        "FROM\n" +
                        "    md_frm f\n" +
                        "        JOIN\n" +
                        "    md_translation t ON t.md_frm_id = f.id\n" +
                        "WHERE\n" +
                        "    f.id = :formId AND t.lang_type = :langType";

        return jdbc.queryForObject(formMetaDataSql, formMetaDataParams,
                (rs, rowNum) ->
                        new FormMetaData.FormMetaDataBuilder()
                                .withUiDescription(rs.getString("ui_description"))
                                .withNumberOfElements(rs.getInt("elem_number"))
                                .withFieldType(FieldType.valueOf(rs.getString("field_type")))
                                .withLanguageType(LanguageType.valueOf(rs.getString("lang_type")))
                                .withFormDescription(rs.getString("text"))
                                .withFormTypes(Collections.singletonList(formType))
                                .build()
        );
    }

    @Override
    public List<CustomerAddress> getAddressesOfAllCustomers() {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
        final String allAddressesSql =
                "SELECT \n" +
                        "    c.first_name,\n" +
                        "    c.last_name,\n" +
                        "    c.email,\n" +
                        "    a.address,\n" +
                        "    a.district,\n" +
                        "    ci.city,\n" +
                        "    co.country\n" +
                        "FROM\n" +
                        "    customer c\n" +
                        "        LEFT OUTER JOIN\n" +
                        "    address a ON c.address_id = a.address_id\n" +
                        "        LEFT OUTER JOIN\n" +
                        "    city ci ON a.city_id = ci.city_id\n" +
                        "        LEFT OUTER JOIN\n" +
                        "    country co ON ci.country_id = co.country_id";
        return jdbc.query(allAddressesSql,
                (rs, rowNum) ->
                        new CustomerAddress.UserAddressBuilder()
                                .withFirstName(rs.getString("first_name"))
                                .withLastName(rs.getString("last_name"))
                                .withEmail(rs.getString("email"))
                                .withAddress(rs.getString("address"))
                                .withDistrict(rs.getString("district"))
                                .withCity(rs.getString("city"))
                                .withCountry(rs.getString("country"))
                                .build()
        );
    }
}
