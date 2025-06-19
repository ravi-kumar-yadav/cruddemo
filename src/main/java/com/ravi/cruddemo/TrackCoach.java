package com.ravi.cruddemo;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{

    public TrackCoach(){
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    /**
     * @return
     */
    @Override
    public String getDailyWorkout() {
       return "Practice jogging for 15min!!!";
    }
}
