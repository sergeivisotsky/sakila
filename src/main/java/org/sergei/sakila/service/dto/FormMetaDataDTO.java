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
    private String uiDescription;
    private Integer numberOfElements;
    private FieldType fieldType;
    private LanguageType languageType;
    private String formDescription;

    private List<FormTypeDTO> formTypes = Collections.emptyList();

    private FormMetaDataDTO() {
    }

    public String getUiDescription() {
        return uiDescription;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public List<FormTypeDTO> getFormTypes() {
        return formTypes;
    }

    public static final class FormMetaDataDTOBuilder {
        private String uiDescription;
        private Integer numberOfElements;
        private FieldType fieldType;
        private LanguageType languageType;
        private String formDescription;
        private List<FormTypeDTO> formTypes = Collections.emptyList();

        private FormMetaDataDTOBuilder() {
        }

        public static FormMetaDataDTOBuilder aFormMetaDataDTO() {
            return new FormMetaDataDTOBuilder();
        }

        public FormMetaDataDTOBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public FormMetaDataDTOBuilder withNumberOfElements(Integer numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public FormMetaDataDTOBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public FormMetaDataDTOBuilder withLanguageType(LanguageType languageType) {
            this.languageType = languageType;
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
            formMetaDataDTO.uiDescription = this.uiDescription;
            formMetaDataDTO.formTypes = this.formTypes;
            formMetaDataDTO.fieldType = this.fieldType;
            formMetaDataDTO.formDescription = this.formDescription;
            formMetaDataDTO.numberOfElements = this.numberOfElements;
            formMetaDataDTO.languageType = this.languageType;
            return formMetaDataDTO;
        }
    }
}
