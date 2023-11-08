package org.example;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebWorkApplication {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }

    public static void main(String[] args) {

        SpringApplication.run(WebWorkApplication.class, args);
    }

}

/*

package com.example.labpsql.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator(){
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

*/