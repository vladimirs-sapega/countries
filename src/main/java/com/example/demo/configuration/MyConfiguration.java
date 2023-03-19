package com.example.demo.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class MyConfiguration {
    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
