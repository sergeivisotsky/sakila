package org.sergei.sakila.rest;

import org.sergei.sakila.jdbc.IDataAccessObject;
import org.sergei.sakila.model.*;
import org.sergei.sakila.rest.dto.CustomerAddressDTO;
import org.sergei.sakila.rest.dto.FormMetaDataDTO;
import org.sergei.sakila.rest.dto.FormTypeDTO;
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
@RequestMapping("/uiapi/v1/sakila/data")
public class ViewController {

    private final IDataAccessObject dao;

    @Autowired
    public ViewController(IDataAccessObject dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/metadata", produces = "application/json")
    public ResponseEntity<FormMetaDataDTO> getFormMetaData(@RequestParam("formId") long formId,
                                                           @RequestParam("langCode") String langCode) {
        FormMetaData fmd = dao.getFormMetaData(formId, langCode);

        List<FormType> formType = fmd.getFormTypes();
        List<FormTypeDTO> formTypeDTO = new LinkedList<>();

        formType.forEach(formType1 -> {
            FormTypeDTO ftDTO = FormTypeDTO.FormTypeDTOBuilder.aFormTypeDTO()
                    .withNumberOfElements(formType.get(0).getNumberOfElements())
                    .withFormDescription(formType.get(0).getFormDescription())
                    .build();

            formTypeDTO.add(ftDTO);
        });

        FormMetaDataDTO fmdDTO = FormMetaDataDTO.FormMetaDataDTOBuilder.aFormMetaDataDTO()
                .withUiDescription(fmd.getUiDescription())
                .withNumberOfElements(fmd.getNumberOfElements())
                .withFieldType(FieldType.valueOf(String.valueOf(fmd.getFieldType())))
                .withLanguageType(LanguageType.valueOf(String.valueOf(fmd.getLanguageType())))
                .withFormDescription(fmd.getFormDescription())
                .withFormTypes(formTypeDTO)
                .build();
        return new ResponseEntity<>(fmdDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses", produces = "application/json")
    public ResponseEntity<List<CustomerAddressDTO>> getAllAddresses() {
        List<CustomerAddress> customerAddresses = dao.getAddressesOfAllCustomers();

        List<CustomerAddressDTO> customerAddressesDTOS = new LinkedList<>();

        customerAddresses.forEach(customerAddress -> {
            CustomerAddressDTO customerAddressDTO = CustomerAddressDTO.CustomerAddressDTOBuilder.aCustomerAddressDTO()
                    .withFirstName(customerAddress.getFirstName())
                    .withLastName(customerAddress.getLastName())
                    .withEmail(customerAddress.getEmail())
                    .withAddress(customerAddress.getAddress())
                    .withDistrict(customerAddress.getDistrict())
                    .withCity(customerAddress.getCity())
                    .withCountry(customerAddress.getCountry())
                    .build();
            customerAddressesDTOS.add(customerAddressDTO);
        });

        return new ResponseEntity<>(customerAddressesDTOS, HttpStatus.OK);
    }


}
