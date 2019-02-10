package org.sergei.sakila.service;

import org.sergei.sakila.jdbc.IDataAccessObject;
import org.sergei.sakila.model.*;
import org.sergei.sakila.service.dto.CustomerAddressDTO;
import org.sergei.sakila.service.dto.FormMetaDataDTO;
import org.sergei.sakila.service.dto.FormTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Service
public class ViewService implements IViewService {

    private final IDataAccessObject dao;

    @Autowired
    public ViewService(IDataAccessObject dao) {
        this.dao = dao;
    }


    @Override
    public FormMetaDataDTO getFormMetaData(long formId, String langCode) {
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

        return FormMetaDataDTO.FormMetaDataDTOBuilder.aFormMetaDataDTO()
                .withUiDescription(fmd.getUiDescription())
                .withNumberOfElements(fmd.getNumberOfElements())
                .withFieldType(FieldType.valueOf(String.valueOf(fmd.getFieldType())))
                .withLanguageType(LanguageType.valueOf(String.valueOf(fmd.getLanguageType())))
                .withFormDescription(fmd.getFormDescription())
                .withFormTypes(formTypeDTO)
                .build();
    }

    @Override
    public List<CustomerAddressDTO> getAllAddresses() {
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

        return customerAddressesDTOS;
    }
}
