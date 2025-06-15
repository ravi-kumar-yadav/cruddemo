package com.ravi.cruddemo;

import com.ravi.cruddemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Lazy
public class Controller {

    private Coach coach;
    private Coach anotherCoach;

    @Autowired
    public Controller(@Qualifier("cricketCoach")  Coach coach, @Qualifier("swimCoach") Coach anotherCoach){
        this.coach = coach;
        this.anotherCoach = anotherCoach;

        System.out.println("In Constructor: " + this.getClass().getSimpleName());
    }

    @GetMapping("/dailyWorkout")
    public String hello(){
        return coach.getDailyWorkout();
    }

    @GetMapping("/checkBeanType")
    public String checkBeanType(){
        return (coach == anotherCoach) ? "Bean type: Singleton": "Bean type: Prototype";
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
