package org.sergei.sakila.service.dto;

import com.google.errorprone.annotations.Immutable;
import org.sergei.sakila.model.FieldType;
import org.sergei.sakila.model.LanguageType;

import java.util.Collections;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class FormMetaDataDTO {
    private String viewName;
    private String uiDescription;
    private FieldType fieldType;
    private LanguageType languageCode;
    private String formDescription;

    private List<FormTypeDTO> formTypes = Collections.emptyList();

    private FormMetaDataDTO() {
    }

    public String getViewName() {
        return viewName;
    }

    public String getUiDescription() {
        return uiDescription;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public LanguageType getLanguageCode() {
        return languageCode;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public List<FormTypeDTO> getFormTypes() {
        return formTypes;
    }

    public static final class FormMetaDataDTOBuilder {
        private String viewName;
        private String uiDescription;
        private FieldType fieldType;
        private LanguageType languageCode;
        private String formDescription;
        private List<FormTypeDTO> formTypes = Collections.emptyList();

        private FormMetaDataDTOBuilder() {
        }

        public static FormMetaDataDTOBuilder aFormMetaDataDTO() {
            return new FormMetaDataDTOBuilder();
        }

        public FormMetaDataDTOBuilder withViewName(String viewName) {
            this.viewName = viewName;
            return this;
        }

        public FormMetaDataDTOBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public FormMetaDataDTOBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public FormMetaDataDTOBuilder withLanguageCode(LanguageType languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public FormMetaDataDTOBuilder withFormDescription(String formDescription) {
            this.formDescription = formDescription;
            return this;
        }

        public FormMetaDataDTOBuilder withFormTypes(List<FormTypeDTO> formTypes) {
            this.formTypes = formTypes;
            return this;
        }

        public FormMetaDataDTO build() {
            FormMetaDataDTO formMetaDataDTO = new FormMetaDataDTO();
            formMetaDataDTO.fieldType = this.fieldType;
            formMetaDataDTO.viewName = this.viewName;
            formMetaDataDTO.languageCode = this.languageCode;
            formMetaDataDTO.formDescription = this.formDescription;
            formMetaDataDTO.formTypes = this.formTypes;
            formMetaDataDTO.uiDescription = this.uiDescription;
            return formMetaDataDTO;
        }
    }
}
