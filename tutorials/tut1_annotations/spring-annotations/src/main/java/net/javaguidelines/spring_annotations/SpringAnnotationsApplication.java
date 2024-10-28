package net.javaguidelines.spring_annotations;

import net.javaguidelines.spring_annotations.controllers.PizzaController;
import net.javaguidelines.spring_annotations.service.VegPizza;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAnnotationsApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringAnnotationsApplication.class, args);
		PizzaController pizzaController= context.getBean(PizzaController.class);
		System.out.println(pizzaController.getPizza());

//		VegPizza vegPizza= context.getBean(VegPizza.class);
//		System.out.println(vegPizza.getPizza());
	}

}
