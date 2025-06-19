package com.ravi.cruddemo;

public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice freestyle for 5min!!!";
    }
}
