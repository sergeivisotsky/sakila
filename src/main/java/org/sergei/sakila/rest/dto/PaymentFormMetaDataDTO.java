package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class PaymentFormMetaDataDTO {
    private String uiDescription;
    private String fieldType;
    private String languageType;

    private PaymentFormMetaDataDTO() {
    }

    public String getUiDescription() {
        return uiDescription;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getLanguageType() {
        return languageType;
    }

    public static final class PaymentFormMetaDataDTOBuilder {
        private String uiDescription;
        private String fieldType;
        private String languageType;

        private PaymentFormMetaDataDTOBuilder() {
        }

        public static PaymentFormMetaDataDTOBuilder aPaymentFormMetaDataDTO() {
            return new PaymentFormMetaDataDTOBuilder();
        }

        public PaymentFormMetaDataDTOBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public PaymentFormMetaDataDTOBuilder withFieldType(String fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public PaymentFormMetaDataDTOBuilder withLanguageType(String languageType) {
            this.languageType = languageType;
            return this;
        }

        public PaymentFormMetaDataDTO build() {
            PaymentFormMetaDataDTO paymentFormMetaDataDTO = new PaymentFormMetaDataDTO();
            paymentFormMetaDataDTO.fieldType = this.fieldType;
            paymentFormMetaDataDTO.languageType = this.languageType;
            paymentFormMetaDataDTO.uiDescription = this.uiDescription;
            return paymentFormMetaDataDTO;
        }
    }
}
