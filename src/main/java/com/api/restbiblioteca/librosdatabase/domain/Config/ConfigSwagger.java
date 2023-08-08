package com.api.restbiblioteca.librosdatabase.domain.Config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentacion de mis primeras API-Rest")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Jose")
                                .url("https://github.com/D4rk61")
                                .email("reynosojose2005@gmail.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://github.com/D4rk61/API-Rest-Biblioteca/blob/main/LICENSE")))
                .components(new Components()
                        .addSecuritySchemes("jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")));

    }
}
