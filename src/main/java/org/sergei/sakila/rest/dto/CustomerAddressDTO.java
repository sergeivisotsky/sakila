package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class CustomerAddressDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String district;
    private String city;
    private String country;

    private CustomerAddressDTO() {
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

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public static final class CustomerAddressDTOBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String district;
        private String city;
        private String country;

        private CustomerAddressDTOBuilder() {
        }

        public static CustomerAddressDTOBuilder aCustomerAddressDTO() {
            return new CustomerAddressDTOBuilder();
        }

        public CustomerAddressDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerAddressDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerAddressDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerAddressDTOBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CustomerAddressDTOBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public CustomerAddressDTOBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public CustomerAddressDTOBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public CustomerAddressDTO build() {
            CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
            customerAddressDTO.city = this.city;
            customerAddressDTO.email = this.email;
            customerAddressDTO.address = this.address;
            customerAddressDTO.country = this.country;
            customerAddressDTO.firstName = this.firstName;
            customerAddressDTO.lastName = this.lastName;
            customerAddressDTO.district = this.district;
            return customerAddressDTO;
        }
    }
}
