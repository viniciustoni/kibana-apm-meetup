package com.vagai.salesorder.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API - Sales Order")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi actuator() {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch("/actuator/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Meetup - Kibana APM - Sales Order")
                        .contact(new Contact()
                                .name("Vinicius Antonio Gai")
                                .url("https://github.com/viniciustoni")));
    }

}
