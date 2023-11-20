package com.escuelaing.demo_appgusto;

import com.escuelaing.demo_appgusto.model.Configuration;
import com.escuelaing.demo_appgusto.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoAppGustoApplication {
    @Autowired
    ConfigurationService configurationService;

    public static void main(String[] args) {

        SpringApplication.run(DemoAppGustoApplication.class, args);
    }
    @Bean
    public CommandLineRunner run() throws Exception {
        return (args) -> {

            System.out.println("Adding Configurations....");
            configurationService.addConfiguration(new Configuration("premio", "800000"));

            System.out.println("\nGetting all configurations....");
            configurationService.getAllConfigurations().forEach(configuration -> System.out.println(configuration));
        };
    }

}
