package com.klm.dev.exercises.devcase01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@PropertySource(value = {"classpath:/config/application.properties"})
@ComponentScan(basePackages = "com.klm.dev.exercises.devcase01")
public class ApplicationConfiguration {

}
