package org.sergei.sakila.jdbc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sergei Visotsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAccessObjectTest {

    @Autowired
    private IDataAccessObject dao;

}
