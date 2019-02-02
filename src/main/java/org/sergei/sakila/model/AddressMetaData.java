package org.sergei.sakila.model;

/**
 * @author Sergei Visotsky
 */
public class AddressMetaData {

    private String uiDescription;
    private FieldType fieldType;

    public AddressMetaData() {
    }

    public AddressMetaData(String uiDescription, FieldType fieldType) {
        this.uiDescription = uiDescription;
        this.fieldType = fieldType;
    }


    public static final class AddressMetaDataBuilder {
        private String uiDescription;
        private FieldType fieldType;

        private AddressMetaDataBuilder() {
        }

        public static AddressMetaDataBuilder anAddressMetaData() {
            return new AddressMetaDataBuilder();
        }

        public AddressMetaDataBuilder withUiDescription(String uiDescription) {
            this.uiDescription = uiDescription;
            return this;
        }

        public AddressMetaDataBuilder withFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public AddressMetaData build() {
            return new AddressMetaData(uiDescription, fieldType);
        }
    }
}
