package com.ravi.cruddemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BadmintonCoach implements Coach{

    public BadmintonCoach(){
        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout(){
        return "Practice badminton for 30min!!!";
    }
}
