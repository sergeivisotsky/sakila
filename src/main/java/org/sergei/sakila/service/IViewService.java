package org.sergei.sakila.service;

import org.sergei.sakila.rest.dto.CustomerAddressDTO;
import org.sergei.sakila.rest.dto.FormMetaDataDTO;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public interface IViewService {
    FormMetaDataDTO getFormMetaData(long formId, String langCode);

    List<CustomerAddressDTO> getAllAddresses();
}
