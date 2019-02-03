package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.PaymentFormData;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public interface IDataAccessObject {
    List<PaymentFormData> getPaymentFromDataAndMetaData(long paymentId);

    Address getAddressWithMetadata(long cityId, long addressId);
}
