package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class PaymentFormMetaData {
    private String uiDescription;
    private FieldType fieldType;
    private LanguageType languageType;

    private PaymentFormMetaData() {
    }

    public String getUiDescription() {
        return uiDescription;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public static final class PaymentFormMetaDataBuilder {
        private String uiDescription;
        private FieldType fieldType;
        private LanguageType languageType;

        public PaymentFormMetaDataBuilder() {
        }

        public static PaymentFormMetaDataBuilder aPaymentFormMetaData() {
            return new PaymentFormMetaDataBuilder();
        }

        public PaymentFormMetaDataBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public PaymentFormMetaDataBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public PaymentFormMetaDataBuilder withLanguageType(LanguageType languageType) {
            this.languageType = languageType;
            return this;
        }

        public PaymentFormMetaData build() {
            PaymentFormMetaData paymentFormMetaData = new PaymentFormMetaData();
            paymentFormMetaData.languageType = this.languageType;
            paymentFormMetaData.fieldType = this.fieldType;
            paymentFormMetaData.uiDescription = this.uiDescription;
            return paymentFormMetaData;
        }
    }
}
