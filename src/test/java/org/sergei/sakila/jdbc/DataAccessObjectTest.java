package org.sergei.sakila.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.sakila.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergei Visotsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAccessObjectTest {

    @Autowired
    private DataAccessObject dao;

    @Test
    public void testDataAccessObject() {
        Address address = dao.getAddressWithMetadata(463, "35200");
        assertEquals("121 Loja Avenue", address.getFirstAddress());
    }
}
