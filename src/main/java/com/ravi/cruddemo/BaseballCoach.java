package com.ravi.cruddemo;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach(){
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    /**
     * @return
     */
    @Override
    public String getDailyWorkout() {
       return "Practice ball throwing for 15min!!!";
    }
}
