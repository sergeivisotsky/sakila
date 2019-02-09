package org.sergei.sakila.config;

import org.sergei.sakila.jdbc.CachingDataAccessObject;
import org.sergei.sakila.jdbc.DataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

/**
 * @author Sergei Visotsky
 */
@Configuration
@ComponentScan("org.sergei.sakila")
@EnableSwagger2
public class SakilaConfigurer {

    private final DatabaseProperties dbProp;

    @Autowired
    public SakilaConfigurer(@Qualifier(value = "databaseProperties")
                                    DatabaseProperties dbProp) {
        this.dbProp = dbProp;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(dbProp.getDriverClassName());
        dataSource.setUrl(dbProp.getUrl());
        dataSource.setUsername(dbProp.getUsername());
        dataSource.setPassword(dbProp.getPassword());

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbc() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.sergei.sakila.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Experimental API methods")
                .description("Methods for experiment")
                .version("1.0")
                .build();
    }

    @Bean
    public CachingDataAccessObject cachingDataAccessObject(DataSource dataSource) {
        return new CachingDataAccessObject(new DataAccessObject(dataSource));
    }

}
