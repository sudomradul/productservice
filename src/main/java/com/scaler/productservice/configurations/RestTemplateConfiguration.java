package com.scaler.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// NOte: when spring starts, classes with @Configuration tag and use methods tagged with Bean to create Objects that can now be managed by SPRING!
// user registers objects with spring for autowiring. These objects are now in ApplicationContext and can be managed by spring.
@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
