package org.sergei.sakila.service;

import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.PaymentFormDataDTO;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public interface IMetaDataService {
    List<PaymentFormDataDTO> getPaymentFromDataAndMetaData(long customerId);

    AddressDTO getAddressWithMetadata(long cityId, long addressId);
}
