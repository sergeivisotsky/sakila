package org.sergei.sakila.rest;

import io.swagger.annotations.ApiOperation;
import org.sergei.sakila.jdbc.DataAccessObject;
import org.sergei.sakila.model.Address;
import org.sergei.sakila.model.AddressMetaData;
import org.sergei.sakila.rest.dto.AddressDTO;
import org.sergei.sakila.rest.dto.AddressMetaDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping("/uiapi/v1/sakila/metadata")
public class ViewController {

    private final DataAccessObject dao;

    @Autowired
    public ViewController(DataAccessObject dao) {
        this.dao = dao;
    }

    @ApiOperation("Get address data and metadata")
    @GetMapping(value = "/get_address_data", produces = "application/json")
    public ResponseEntity<AddressDTO> getAddressDataAndMeta(@RequestParam("cityId") long cityId,
                                                            @RequestParam("postalCode") String postalCode) {
        Address address = dao.getAddressWithMetadata(cityId, postalCode);
        List<AddressMetaData> addressMetaData = address.getAddressMetadata();
/*        if (address.equals(0) || addressMetaData.equals(0)) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        List<AddressMetaDataDTO> addressMetaDataDTOList = new LinkedList<>();

        for (AddressMetaData addressMeta : addressMetaData) {
            AddressMetaDataDTO addressMetaDataDTO = AddressMetaDataDTO.AddressMetaDataDTOBuilder.anAddressMetaDataDTO()
                    .withFieldType(addressMeta.getFieldType())
                    .withUiDescription(addressMeta.getUiDescription())
                    .build();
            addressMetaDataDTOList.add(addressMetaDataDTO);
        }

        AddressDTO addressDTO = AddressDTO.AddressDTOBuilder.anAddressDTO()
                .withFirstAddress(address.getFirstAddress())
                .withSecondAddress(address.getSecondAddress())
                .withDistrict(address.getDistrict())
                .withPostalCode(address.getPostalCode())
                .withAddressMetadata(addressMetaDataDTOList)
                .build();

        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }
}
