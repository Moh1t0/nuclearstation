package com.javaacademy.nuclearstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NuclearstationApplication {

    private static final int YEARS = 3;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NuclearstationApplication.class, args);
        NuclearStation nuclearStation = context.getBean(NuclearStation.class);
        nuclearStation.start(YEARS);
    }
}
