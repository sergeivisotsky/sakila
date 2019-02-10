package org.sergei.sakila.rest;

import org.sergei.sakila.service.dto.CustomerAddressDTO;
import org.sergei.sakila.service.dto.FormMetaDataDTO;
import org.sergei.sakila.service.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping("/uiapi/v1/sakila/data")
public class ViewController {

    private final IViewService viewService;

    @Autowired
    public ViewController(IViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping(value = "/metadata", produces = "application/json")
    public ResponseEntity<FormMetaDataDTO> getFormMetaData(@RequestParam("formId") long formId,
                                                           @RequestParam("langCode") String langCode) {
        return new ResponseEntity<>(viewService.getFormMetaData(formId, langCode), HttpStatus.OK);
    }

    @GetMapping(value = "/addresses", produces = "application/json")
    public ResponseEntity<List<CustomerAddressDTO>> getAllAddresses() {
        return new ResponseEntity<>(viewService.getAllAddresses(), HttpStatus.OK);
    }


}
