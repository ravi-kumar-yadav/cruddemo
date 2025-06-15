package com.ravi.cruddemo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {

    // As this object isn't being managed via @Component annotation
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
