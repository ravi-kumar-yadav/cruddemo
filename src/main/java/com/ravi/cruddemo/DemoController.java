package com.ravi.cruddemo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Lazy
public class DemoController {

    Coach coach;
    Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach coach, @Qualifier("swimCoach") Coach anotherCoach) {
        this.coach = coach;
        this.anotherCoach = anotherCoach;
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    @PostConstruct
    public void startupTasks(){
        System.out.println("In Postconstruct: " + this.getClass().getSimpleName());
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkout(){
        return coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String checkBeanType(){
        return (coach == anotherCoach) ? "Singleton Bean Type" : "Prototype Bean Type";
    }
}
