package dev.lkeleti.aticrm;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AticrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AticrmApplication.class, args);
	}

	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("AtiCrm API")
						.version("1.0.0")
						.description("Operations to register partners."));
	}
}
