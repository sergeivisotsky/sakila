package org.sergei.sakila.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author Sergei Visotsky
 */
@Repository
public class DataAccessObject implements IDataAccessObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessObject.class);

    private final DataSource dataSource;

    @Autowired
    public DataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
