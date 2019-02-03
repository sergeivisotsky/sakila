package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.PaymentFormData;

/**
 * @author Sergei Visotsky
 */
public interface IDataAccessObject {
    PaymentFormData getPaymentFromDataAndMetaData(long paymentId);

    Address getAddressWithMetadata(long cityId, String postalCode);
}
