package com.ravi.cruddemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){
        return "Practice forehand for 30min!!!";
    }
}
