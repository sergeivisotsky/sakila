package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.CustomerAddress;
import org.sergei.sakila.model.FormMetaData;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
public interface IDataAccessObject {

    FormMetaData getFormMetaData(long formId, String viewName, String langType);

    List<CustomerAddress> getAddressesOfAllCustomers();
}
