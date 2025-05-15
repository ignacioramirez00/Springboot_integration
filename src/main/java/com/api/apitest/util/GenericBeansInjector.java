package com.api.apitest.util;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class GenericBeansInjector {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    
}
