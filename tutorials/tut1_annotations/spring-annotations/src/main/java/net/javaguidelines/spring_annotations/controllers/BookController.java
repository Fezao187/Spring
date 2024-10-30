package net.javaguidelines.spring_annotations.controllers;

import net.javaguidelines.spring_annotations.beans.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @RequestMapping("/hello-world")
    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/book")
    @ResponseBody
    public Book getBook() {
        Book book = new Book(1,"Core Java", "Learn core Java and features");
        return book;
    }
}
