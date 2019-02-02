package org.sergei.sakila.model;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public class Address {
//    private String city;
    private String firstAddress;
    private String secondAddress;
    private String district;
    private String postalCode;
    private List<AddressMetaData> addressMetadata;

    public Address() {
    }

    public static final class AddressBuilder {
//        private String city;
        private String firstAddress;
        private String secondAddress;
        private String district;
        private String postalCode;
        private List<AddressMetaData> addressMetadata;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        /*public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }
*/
        public AddressBuilder withFirstAddress(String firstAddress) {
            this.firstAddress = firstAddress;
            return this;
        }

        public AddressBuilder withSecondAddress(String secondAddress) {
            this.secondAddress = secondAddress;
            return this;
        }

        public AddressBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public AddressBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressBuilder withAddressMetadata(List<AddressMetaData> addressMetadata) {
            this.addressMetadata = addressMetadata;
            return this;
        }

        public Address build() {
            Address address = new Address();
//            address.city = this.city;
            address.secondAddress = this.secondAddress;
            address.firstAddress = this.firstAddress;
            address.postalCode = this.postalCode;
            address.district = this.district;
            address.addressMetadata = this.addressMetadata;
            return address;
        }
    }
}
