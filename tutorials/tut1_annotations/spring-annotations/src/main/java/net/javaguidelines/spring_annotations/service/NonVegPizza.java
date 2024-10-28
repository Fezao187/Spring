package net.javaguidelines.spring_annotations.service;

//If not specified, this will let spring inject this to the controller
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


public class NonVegPizza implements Pizza {

    @Override
    public String getPizza() {
        return "Non Veg Pizza";
    }
}
