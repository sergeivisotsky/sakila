package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class AddressDTO {
    private String firstAddress;
    private String secondAddress;
    private String district;
    private String postalCode;
    private List<AddressMetaDataDTO> addressMetadata;

    private AddressDTO() {
    }

    public static final class AddressDTOBuilder {
        private String firstAddress;
        private String secondAddress;
        private String district;
        private String postalCode;
        private List<AddressMetaDataDTO> addressMetadata;

        private AddressDTOBuilder() {
        }

        public static AddressDTOBuilder anAddressDTO() {
            return new AddressDTOBuilder();
        }

        public AddressDTOBuilder withFirstAddress(String firstAddress) {
            this.firstAddress = firstAddress;
            return this;
        }

        public AddressDTOBuilder withSecondAddress(String secondAddress) {
            this.secondAddress = secondAddress;
            return this;
        }

        public AddressDTOBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public AddressDTOBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressDTOBuilder withAddressMetadata(List<AddressMetaDataDTO> addressMetadata) {
            this.addressMetadata = addressMetadata;
            return this;
        }

        public AddressDTO build() {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.secondAddress = this.secondAddress;
            addressDTO.district = this.district;
            addressDTO.firstAddress = this.firstAddress;
            addressDTO.addressMetadata = this.addressMetadata;
            addressDTO.postalCode = this.postalCode;
            return addressDTO;
        }
    }
}
