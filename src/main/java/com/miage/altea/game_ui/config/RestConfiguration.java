package com.miage.altea.game_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
