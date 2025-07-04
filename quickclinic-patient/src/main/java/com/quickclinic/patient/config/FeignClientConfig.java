package com.quickclinic.patient.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            // Get the currently authenticated user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            // If JWT is available, attach it as Authorization header
            if (auth!=null && auth.getCredentials() != null){
                String token = auth.getCredentials().toString();
                requestTemplate.header("Authorization","Bearer " + token);
            }
        };
    }
}
