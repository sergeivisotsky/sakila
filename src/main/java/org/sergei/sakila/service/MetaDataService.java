package org.sergei.sakila.service;

import org.sergei.sakila.jdbc.IDataAccessObject;
import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
import org.sergei.sakila.model.PaymentFormData;
import org.sergei.sakila.model.PaymentFormMetaData;
import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.AddressMetaDataDTO;
import org.sergei.sakila.rest.dto.PaymentFormDataDTO;
import org.sergei.sakila.rest.dto.PaymentFormMetaDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Service
public class MetaDataService implements IMetaDataService {

    private final IDataAccessObject dao;

    @Autowired
    public MetaDataService(IDataAccessObject dao) {
        this.dao = dao;
    }

    @Override
    public PaymentFormDataDTO getPaymentFromDataAndMetaData(long paymentId) {
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

        return PaymentFormDataDTO.PaymentFormDataDTOBuilder.aPaymentFormDataDTO()
                .withFirstName(paymentFormData.getFirstName())
                .withLastName(paymentFormData.getLastName())
                .withEmail(paymentFormData.getEmail())
                .withCreateDate(paymentFormData.getCreateDate())
                .withLastUpdate(paymentFormData.getLastUpdate())
                .withRentalId(paymentFormData.getRentalId())
                .withAmount(paymentFormData.getAmount())
                .build();
    }

    @Override
    public AddressDTO getAddressWithMetadata(long cityId, String postalCode) {
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

        return AddressDTO.AddressDTOBuilder.anAddressDTO()
                .withFirstAddress(address.getFirstAddress())
                .withSecondAddress(address.getSecondAddress())
                .withDistrict(address.getDistrict())
                .withPostalCode(address.getPostalCode())
                .withAddressMetadata(addressMetaDataDTOList)
                .build();
    }
}
