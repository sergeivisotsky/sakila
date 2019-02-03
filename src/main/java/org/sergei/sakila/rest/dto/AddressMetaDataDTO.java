package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;
import org.sergei.sakila.model.FieldType;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class AddressMetaDataDTO {
    private String uiDescription;
    private FieldType fieldType;

    private AddressMetaDataDTO() {
    }

    public String getUiDescription() {
        return uiDescription;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public static final class AddressMetaDataDTOBuilder {
        private String uiDescription;
        private FieldType fieldType;

        private AddressMetaDataDTOBuilder() {
        }

        public static AddressMetaDataDTOBuilder anAddressMetaDataDTO() {
            return new AddressMetaDataDTOBuilder();
        }

        public AddressMetaDataDTOBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public AddressMetaDataDTOBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public AddressMetaDataDTO build() {
            AddressMetaDataDTO addressMetaDataDTO = new AddressMetaDataDTO();
            addressMetaDataDTO.uiDescription = this.uiDescription;
            addressMetaDataDTO.fieldType = this.fieldType;
            return addressMetaDataDTO;
        }
    }

    @Override
    public String toString() {
        return "AddressMetaDataDTO{" +
                "uiDescription='" + uiDescription + '\'' +
                ", fieldType=" + fieldType +
                '}';
    }
}
