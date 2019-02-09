package org.sergei.sakila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SakilaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SakilaApplication.class);

    public static void getBeans(ApplicationContext applicationContext) {
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            LOGGER.debug("Bean: {}", applicationContext.getBean(bean));
        }
    }

    public static void main(String[] args) {

        if (System.getProperty("-Dspring.profiles.active") == null) {
            System.setProperty("-Dspring.profiles.active", "prod");
        }

        ApplicationContext applicationContext = SpringApplication.run(SakilaApplication.class, args);

        getBeans(applicationContext);
    }
}