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
        String[] formDescription = new String[1];
        FormType formType = jdbc.query("call get_form_type(:formId)", paramsFormType,
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
        return jdbc.queryForObject("call get_form_metadata(:formId, :langType)", formMetaDataParams,
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
        return jdbc.query("call get_addresses_of_all_customers()",
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
