package org.sergei.sakila.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.sakila.model.FormMetaData;
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
    private IDataAccessObject dao;

    @Test
    public void getFormMetaDataTest() {
        FormMetaData formMetaData = dao.getFormMetaData(1, "LV");
        assertEquals("This is test UI description", formMetaData.getUiDescription());
    }
}
