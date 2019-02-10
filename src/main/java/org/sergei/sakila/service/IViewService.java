package org.sergei.sakila.service;

import org.sergei.sakila.service.dto.CustomerAddressDTO;
import org.sergei.sakila.service.dto.FormMetaDataDTO;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public interface IViewService {
    FormMetaDataDTO getFormMetaData(long formId, String viewName, String langCode);

    List<CustomerAddressDTO> getAllAddresses();
}
