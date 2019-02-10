package org.sergei.sakila.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.sakila.model.CustomerAddress;
import org.sergei.sakila.model.FormMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergei Visotsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAccessObjectTest {

    @Autowired
    private IDataAccessObject dao;

    @Test
    public void getFormMetaDataTest() {
        FormMetaData formMetaData = dao.getFormMetaData(1, "cusotmer_view", "LV");
        assertEquals("This is test UI description", formMetaData.getUiDescription());
    }

    @Test
    public void getAddressesOfAllCustomersTest() {
        List<CustomerAddress> customerAddresses = dao.getAddressesOfAllCustomers();
        assertEquals("MARY", customerAddresses.get(0).getFirstName());
    }
}
