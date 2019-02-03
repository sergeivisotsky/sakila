package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class Address {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String district;
    private Long cityId;
    private String city;
    private List<AddressMetaData> addressMetadata;

    private Address() {
    }

    public List<AddressMetaData> getAddressMetadata() {
        return addressMetadata;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public static final class AddressBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String district;
        private Long cityId;
        private String city;
        private List<AddressMetaData> addressMetadata;

        public AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AddressBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AddressBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AddressBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AddressBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public AddressBuilder withCityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withAddressMetadata(List<AddressMetaData> addressMetadata) {
            this.addressMetadata = addressMetadata;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.firstName = this.firstName;
            address.city = this.city;
            address.addressMetadata = this.addressMetadata;
            address.cityId = this.cityId;
            address.district = this.district;
            address.email = this.email;
            address.address = this.address;
            address.lastName = this.lastName;
            return address;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", cityId=" + cityId +
                ", city='" + city + '\'' +
                ", addressMetadata=" + addressMetadata +
                '}';
    }
}
