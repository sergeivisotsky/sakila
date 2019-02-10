package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

import java.util.Collections;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class FormMetaData {
    private String viewName;
    private String uiDescription;
    private FieldType fieldType;
    private LanguageType languageCode;
    private String formDescription;

    private List<FormType> formTypes = Collections.emptyList();

    private FormMetaData() {
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

    public List<FormType> getFormTypes() {
        return formTypes;
    }

    public static final class FormMetaDataBuilder {
        private String viewName;
        private String uiDescription;
        private FieldType fieldType;
        private LanguageType languageCode;
        private String formDescription;
        private List<FormType> formTypes = Collections.emptyList();

        public FormMetaDataBuilder() {
        }

        public static FormMetaDataBuilder aFormMetaData() {
            return new FormMetaDataBuilder();
        }

        public FormMetaDataBuilder withViewName(String viewName) {
            this.viewName = viewName;
            return this;
        }

        public FormMetaDataBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public FormMetaDataBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public FormMetaDataBuilder withLanguageCode(LanguageType languageCode) {
            this.languageCode = languageCode;
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
            formMetaData.formTypes = this.formTypes;
            formMetaData.formDescription = this.formDescription;
            formMetaData.uiDescription = this.uiDescription;
            formMetaData.fieldType = this.fieldType;
            formMetaData.viewName = this.viewName;
            formMetaData.languageCode = this.languageCode;
            return formMetaData;
        }
    }
}
