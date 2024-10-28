package net.javaguidelines.spring_annotations.service;

public class VegPizza implements Pizza {

    @Override
    public String getPizza(){
        return "Veg Pizza";
    }
}
