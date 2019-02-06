package org.sergei.sakila.service;

import org.sergei.sakila.jdbc.IDataAccessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sergei Visotsky
 */
@Service
public class MetaDataService implements IMetaDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataService.class);

    private final IDataAccessObject dao;

    @Autowired
    public MetaDataService(IDataAccessObject dao) {
        this.dao = dao;
    }


}
