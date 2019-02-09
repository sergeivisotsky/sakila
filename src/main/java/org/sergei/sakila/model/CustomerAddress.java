package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class CustomerAddress {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String district;
    private String city;
    private String country;

    private CustomerAddress() {
    }

    public Long getCustomerId() {
        return customerId;
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

    public static final class UserAddressBuilder {
        private Long customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String district;
        private String city;
        private String country;

        public UserAddressBuilder() {
        }

        public static UserAddressBuilder anUserAddress() {
            return new UserAddressBuilder();
        }

        public UserAddressBuilder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public UserAddressBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserAddressBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserAddressBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserAddressBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserAddressBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public UserAddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public UserAddressBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public CustomerAddress build() {
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.city = this.city;
            customerAddress.customerId = this.customerId;
            customerAddress.firstName = this.firstName;
            customerAddress.lastName = this.lastName;
            customerAddress.country = this.country;
            customerAddress.email = this.email;
            customerAddress.district = this.district;
            customerAddress.address = this.address;
            return customerAddress;
        }
    }
}
