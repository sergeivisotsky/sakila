package org.sergei.sakila.jdbc;

import org.sergei.sakila.model.FormMetaData;

/**
 * @author Sergei Visotsky
 */
public interface IDataAccessObject {

    FormMetaData getFormMetaData(long formId, String langType);

}
