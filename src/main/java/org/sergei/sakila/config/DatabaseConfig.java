package org.sergei.sakila.config;

import org.sergei.sakila.config.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author Sergei Visotsky
 */
@Configuration
@ComponentScan("org.sergei.sakila")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila_dev");
        dataSource.setUsername("service");
        dataSource.setPassword("service");

        return dataSource;
    }


}
