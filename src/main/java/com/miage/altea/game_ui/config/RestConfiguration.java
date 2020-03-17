package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String username;

    @Value("${trainer.service.password}")
    private String password;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Autowired
    public void configureLocalInterceptor(RestTemplate restTemplate){
        restTemplate.setInterceptors(List.of((httpRequest, bytes, clientHttpRequestExecution) -> {
            httpRequest.getHeaders().add(HttpHeaders.ACCEPT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag());
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }));
    }

    @Bean
    public RestTemplate trainerApiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new BasicAuthenticationInterceptor(username, password)));
        return restTemplate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
