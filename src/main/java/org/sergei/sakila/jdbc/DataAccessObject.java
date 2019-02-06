package org.sergei.sakila.jdbc;

import org.sergei.sakila.Loggers;
import org.sergei.sakila.model.FieldType;
import org.sergei.sakila.model.FormMetaData;
import org.sergei.sakila.model.FormType;
import org.sergei.sakila.model.LanguageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;

/**
 * @author Sergei Visotsky
 */
@Repository
public class DataAccessObject implements IDataAccessObject {

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public DataAccessObject(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public FormMetaData getFormMetaData(long formId, String langType) {
        MapSqlParameterSource paramsFormType = new MapSqlParameterSource("formId", formId);
        final String formTypeSql =
                "SELECT " +
                        "    ft.num_of_elem, ft.frm_descr " +
                        "FROM " +
                        "    md_frm_type ft " +
                        "WHERE " +
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

        MapSqlParameterSource formMetaDataParams = new MapSqlParameterSource()
                .addValue("formId", formId)
                .addValue("langType", langType);

        final String formMetaDataSql =
                "SELECT " +
                        "    f.ui_description, f.elem_number, f.field_type, t.lang_type, t.text " +
                        "FROM " +
                        "    md_frm f " +
                        "        JOIN " +
                        "    md_translation t ON t.md_frm_id = f.id " +
                        "WHERE " +
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
}
