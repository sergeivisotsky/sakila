package org.sergei.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SakilaApplication {

    public static void main(String[] args) {

        if (System.getProperty("-Dspring.profiles.active") == null) {
            System.setProperty("-Dspring.profiles.active", "dev");
        }

        SpringApplication.run(SakilaApplication.class, args);
    }

}

