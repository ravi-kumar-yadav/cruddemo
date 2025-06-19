package com.ravi.cruddemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {

    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
