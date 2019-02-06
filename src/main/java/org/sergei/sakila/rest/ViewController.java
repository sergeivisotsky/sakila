package org.sergei.sakila.rest;

import org.sergei.sakila.service.IMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping("/uiapi/v1/sakila/data")
public class ViewController {

    private final IMetaDataService metaDataService;

    @Autowired
    public ViewController(IMetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }

}
