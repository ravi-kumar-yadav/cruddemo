package com.ravi.cruddemo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// No @Component annotation as it's being managed via @Configuration annotation
// Please check SportsConfig Class
public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }


    /**
     * @return
     */
    @Override
    public String getDailyWorkout() {
        return "Practice freestyle for 10min!!!";
    }
}
