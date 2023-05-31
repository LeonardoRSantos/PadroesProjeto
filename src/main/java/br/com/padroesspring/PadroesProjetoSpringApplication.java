package br.com.padroesspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os Seguintes módulos foram Selecionados:
 * -Spring Data JPA
 * -Spring WEB
 * -H2 Database
 * -OpenFeign
 *
 * @author LeonardoRSantos
 */

@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class PadroesProjetoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetoSpringApplication.class, args);
	}

}
