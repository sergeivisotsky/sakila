package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

import java.util.Collections;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class FormMetaData {
    private String uiDescription;
    private Integer numberOfElements;
    private FieldType fieldType;
    private LanguageType languageType;
    private String formDescription;

    private List<FormType> formTypes = Collections.emptyList();

    private FormMetaData() {
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

    public List<FormType> getFormTypes() {
        return formTypes;
    }

    public static final class FormMetaDataBuilder {
        private String uiDescription;
        private Integer numberOfElements;
        private FieldType fieldType;
        private LanguageType languageType;
        private String formDescription;
        private List<FormType> formTypes = Collections.emptyList();

        public FormMetaDataBuilder() {
        }

        public static FormMetaDataBuilder aFormMetaData() {
            return new FormMetaDataBuilder();
        }

        public FormMetaDataBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public FormMetaDataBuilder withNumberOfElements(Integer numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public FormMetaDataBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public FormMetaDataBuilder withLanguageType(LanguageType languageType) {
            this.languageType = languageType;
            return this;
        }

        public FormMetaDataBuilder withFormDescription(String formDescription) {
            this.formDescription = formDescription;
            return this;
        }

        public FormMetaDataBuilder withFormTypes(List<FormType> formTypes) {
            this.formTypes = formTypes;
            return this;
        }

        public FormMetaData build() {
            FormMetaData formMetaData = new FormMetaData();
            formMetaData.languageType = this.languageType;
            formMetaData.fieldType = this.fieldType;
            formMetaData.formTypes = this.formTypes;
            formMetaData.formDescription = this.formDescription;
            formMetaData.uiDescription = this.uiDescription;
            formMetaData.numberOfElements = this.numberOfElements;
            return formMetaData;
        }
    }
}
