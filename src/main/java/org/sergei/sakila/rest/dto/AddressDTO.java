package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class AddressDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String district;
    private Long cityId;
    private String city;
    private List<AddressMetaDataDTO> addressMetadata;

    private AddressDTO() {
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

    public List<AddressMetaDataDTO> getAddressMetadata() {
        return addressMetadata;
    }

    public static final class AddressDTOBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String district;
        private Long cityId;
        private String city;
        private List<AddressMetaDataDTO> addressMetadata;

        private AddressDTOBuilder() {
        }

        public static AddressDTOBuilder anAddressDTO() {
            return new AddressDTOBuilder();
        }

        public AddressDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AddressDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AddressDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AddressDTOBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AddressDTOBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public AddressDTOBuilder withCityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public AddressDTOBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressDTOBuilder withAddressMetadata(List<AddressMetaDataDTO> addressMetadata) {
            this.addressMetadata = addressMetadata;
            return this;
        }

        public AddressDTO build() {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.lastName = this.lastName;
            addressDTO.email = this.email;
            addressDTO.district = this.district;
            addressDTO.firstName = this.firstName;
            addressDTO.cityId = this.cityId;
            addressDTO.addressMetadata = this.addressMetadata;
            addressDTO.address = this.address;
            addressDTO.city = this.city;
            return addressDTO;
        }
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
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
