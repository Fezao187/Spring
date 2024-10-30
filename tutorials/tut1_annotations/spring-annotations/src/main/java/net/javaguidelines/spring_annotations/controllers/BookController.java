package net.javaguidelines.spring_annotations.controllers;

import net.javaguidelines.spring_annotations.beans.Book;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller // For spring MVC apps
//@ResponseBody
@RestController // for rest apis
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
//    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = {"/book", "/core-java", "/java"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_VALUE
    )
//    @ResponseBody
    public Book getBook() {
        Book book = new Book(1, "Core Java", "Learn core Java and features");
        return book;
    }
}
