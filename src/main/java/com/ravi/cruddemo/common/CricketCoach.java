package com.ravi.cruddemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }

    /**
     * @return
     */
    @Override
    public String getDailyWorkout() {
       return "Practice Bowling for 30min!!!";
    }

    @PostConstruct
    public void startupMethod(){
        System.out.println("In startupMethod(): Postconstruct " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void cleanupMethod(){
        System.out.println("In cleanupMethod(): Predestroy " + this.getClass().getSimpleName());
    }
}
