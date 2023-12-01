package co.edu.escuelaing.project.AppGusto;


import co.edu.escuelaing.project.AppGusto.model.Configuration;

import co.edu.escuelaing.project.AppGusto.service.ConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AppGustoApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppGustoApplication.class, args);
	}


}