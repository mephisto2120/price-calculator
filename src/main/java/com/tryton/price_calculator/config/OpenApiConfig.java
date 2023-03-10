package com.tryton.price_calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api(Environment environment) {
        return new OpenAPI().addServersItem(new Server().url("/"))
                .info(new Info().title("Price calculator")
                        .description("This price calculator service was developed to calculate discount of shop.")
                        .version("v1")
                        .contact(new Contact().name("mephisto")
                                .email("mephisto2120@gmail.com"))
                        .license(new License().name("LGPL")
                                .url(""))
                        .extensions(Collections.singletonMap(environment.getActiveProfiles()[0], Strings.join(Arrays.asList(environment.getActiveProfiles()), ','))));
    }
}
