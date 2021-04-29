package com.ailtonluiz.sgdapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class SgdApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgdApiApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
