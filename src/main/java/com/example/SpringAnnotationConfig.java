package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAnnotationConfig {

    @Bean
    public MessageService messageService() {
        return new SimpleMessageService();
    }
}
