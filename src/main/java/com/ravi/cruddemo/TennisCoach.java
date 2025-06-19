package com.ravi.cruddemo;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("prototype")
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    /**
     * @return
     */
    @Override
    public String getDailyWorkout() {
        return "Practice backhand for 15min!!!";
    }
}
