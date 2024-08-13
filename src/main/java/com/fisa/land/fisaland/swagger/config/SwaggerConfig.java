package com.fisa.land.fisaland.swagger.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "fisaland swagger 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
	 @Bean
	    public OpenAPI openAPI() {
	        // Return an OpenAPI instance with basic API metadata
	        return new OpenAPI();
	  }
}