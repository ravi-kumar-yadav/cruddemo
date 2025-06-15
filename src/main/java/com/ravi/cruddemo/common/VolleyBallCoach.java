package com.ravi.cruddemo.common;

import org.springframework.stereotype.Component;

@Component
public class VolleyBallCoach implements Coach{

    public VolleyBallCoach(){
        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){
        return "Practice Volley for 30min!!!";
    }
}
