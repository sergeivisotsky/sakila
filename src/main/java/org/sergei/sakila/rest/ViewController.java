package org.sergei.sakila.rest;

import io.swagger.annotations.ApiOperation;
import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.PaymentFormDataDTO;
import org.sergei.sakila.service.IMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping("/uiapi/v1/sakila/metadata")
public class ViewController {

    private final IMetaDataService metaDataService;

    @Autowired
    public ViewController(IMetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }

    @ApiOperation("Get payment data and metadata")
    @GetMapping(value = "/get_payment_data", params = "paymentId", produces = "application/json")
    public ResponseEntity<PaymentFormDataDTO> getPaymentDataAndMeta(@RequestParam("paymentId") long paymentId) {
        return new ResponseEntity<>(metaDataService.getPaymentFromDataAndMetaData(paymentId), HttpStatus.OK);
    }

    @ApiOperation("Get address data and metadata")
    @GetMapping(value = "/get_address_data", params = {"cityId", "postalCode"}, produces = "application/json")
    public ResponseEntity<AddressDTO> getAddressDataAndMeta(@RequestParam("cityId") long cityId,
                                                            @RequestParam("postalCode") String postalCode) {
        return new ResponseEntity<>(metaDataService.getAddressWithMetadata(cityId, postalCode), HttpStatus.OK);
    }
}
