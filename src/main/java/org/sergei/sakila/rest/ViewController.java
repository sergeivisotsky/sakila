package org.sergei.sakila.rest;

import io.swagger.annotations.ApiOperation;
import org.sergei.sakila.jdbc.DataAccessObject;
import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
import org.sergei.sakila.model.PaymentFormData;
import org.sergei.sakila.model.PaymentFormMetaData;
import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.AddressMetaDataDTO;
import org.sergei.sakila.rest.dto.PaymentFormDataDTO;
import org.sergei.sakila.rest.dto.PaymentFormMetaDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping("/uiapi/v1/sakila/metadata")
public class ViewController {

    private final DataAccessObject dao;

    @Autowired
    public ViewController(DataAccessObject dao) {
        this.dao = dao;
    }

    @ApiOperation("Get payment data and metadata")
    @GetMapping(value = "/get_payment_data", params = "paymentId", produces = "application/json")
    public ResponseEntity<PaymentFormDataDTO> getPaymentDataAndMeta(@RequestParam("paymentId") long paymentId) {
        PaymentFormData paymentFormData = dao.getPaymentFromDataAndMetaData(paymentId);

        List<PaymentFormMetaData> pfmd = paymentFormData.getPaymentFormMetaData();

        // Conversion from data object to DTO started form this place
        List<PaymentFormMetaDataDTO> paymentFormDataDTOList = new LinkedList<>();

        for (PaymentFormMetaData paymentFormMetaData : pfmd) {
            PaymentFormMetaDataDTO pfmdDTO = PaymentFormMetaDataDTO.PaymentFormMetaDataDTOBuilder
                    .aPaymentFormMetaDataDTO()
                    .withUiDescription(paymentFormMetaData.getUiDescription())
                    .withFieldType(String.valueOf(paymentFormMetaData.getLanguageType()))
                    .withLanguageType(String.valueOf(paymentFormMetaData.getLanguageType()))
                    .build();
            paymentFormDataDTOList.add(pfmdDTO);
        }
        PaymentFormDataDTO paymentFormDataDTO = PaymentFormDataDTO.PaymentFormDataDTOBuilder.aPaymentFormDataDTO()
                .withFirstName(paymentFormData.getFirstName())
                .withLastName(paymentFormData.getLastName())
                .withEmail(paymentFormData.getEmail())
                .withCreateDate(paymentFormData.getCreateDate())
                .withLastUpdate(paymentFormData.getLastUpdate())
                .withRentalId(paymentFormData.getRentalId())
                .withAmount(paymentFormData.getAmount())
                .build();

        return new ResponseEntity<>(paymentFormDataDTO, HttpStatus.OK);
    }

    @ApiOperation("Get address data and metadata")
    @GetMapping(value = "/get_address_data", params = {"cityId", "postalCode"}, produces = "application/json")
    public ResponseEntity<AddressDTO> getAddressDataAndMeta(@RequestParam("cityId") long cityId,
                                                            @RequestParam("postalCode") String postalCode) {
        Address address = dao.getAddressWithMetadata(cityId, postalCode);
        List<AddressMetaData> addressMetaData = address.getAddressMetadata();
/*        if (address.equals(0) || addressMetaData.equals(0)) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        // Conversion from data object to DTO started form this place
        List<AddressMetaDataDTO> addressMetaDataDTOList = new LinkedList<>();

        for (AddressMetaData addressMeta : addressMetaData) {
            AddressMetaDataDTO addressMetaDataDTO = AddressMetaDataDTO.AddressMetaDataDTOBuilder.anAddressMetaDataDTO()
                    .withFieldType(addressMeta.getFieldType())
                    .withUiDescription(addressMeta.getUiDescription())
                    .build();
            addressMetaDataDTOList.add(addressMetaDataDTO);
        }

        AddressDTO addressDTO = AddressDTO.AddressDTOBuilder.anAddressDTO()
                .withFirstAddress(address.getFirstAddress())
                .withSecondAddress(address.getSecondAddress())
                .withDistrict(address.getDistrict())
                .withPostalCode(address.getPostalCode())
                .withAddressMetadata(addressMetaDataDTOList)
                .build();

        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }
}
