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

    private Address() {
    }

    public String getFirstAddress() {
        return firstAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public String getDistrict() {
        return district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<AddressMetaData> getAddressMetadata() {
        return addressMetadata;
    }

    public static final class AddressBuilder {
        //    private String city;
        private String firstAddress;
        private String secondAddress;
        private String district;
        private String postalCode;
        private List<AddressMetaData> addressMetadata;

        public AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

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
            address.firstAddress = this.firstAddress;
            address.secondAddress = this.secondAddress;
            address.addressMetadata = this.addressMetadata;
            address.postalCode = this.postalCode;
            address.district = this.district;
            return address;
        }
    }
}
