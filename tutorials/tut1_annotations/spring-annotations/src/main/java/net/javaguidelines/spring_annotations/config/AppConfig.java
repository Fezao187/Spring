package net.javaguidelines.spring_annotations.config;

import net.javaguidelines.spring_annotations.controllers.PizzaController;
import net.javaguidelines.spring_annotations.service.NonVegPizza;
import net.javaguidelines.spring_annotations.service.Pizza;
import net.javaguidelines.spring_annotations.service.VegPizza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class AppConfig {
    @Bean
    public Pizza vegPizza() {
        return new VegPizza();
    }

    @Bean
    public Pizza nonVegPizza() {
        return new NonVegPizza();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PizzaController pizzaController() {
        return new PizzaController(nonVegPizza());
    }
}
