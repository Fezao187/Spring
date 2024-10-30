package net.javaguidelines.spring_annotations.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String hello(){
        return "Hello Controller.";
    }
}
