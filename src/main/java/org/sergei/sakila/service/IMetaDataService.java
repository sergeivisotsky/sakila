package org.sergei.sakila.service;

import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.PaymentFormDataDTO;

/**
 * @author Sergei Visotsky
 */
public interface IMetaDataService {
    PaymentFormDataDTO getPaymentFromDataAndMetaData(long paymentId);

    AddressDTO getAddressWithMetadata(long cityId, long addressId);
}
