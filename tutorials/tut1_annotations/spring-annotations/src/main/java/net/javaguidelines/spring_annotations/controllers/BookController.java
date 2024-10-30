package net.javaguidelines.spring_annotations.controllers;

import net.javaguidelines.spring_annotations.beans.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //    @RequestMapping(value = {"/book", "/core-java", "/java"},
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE
////            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
    @GetMapping(value = {"/book", "/core-java", "/java"},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @ResponseBody
    public Book getBook() {
        Book book = new Book(1, "Core Java", "Learn core Java and features");
        return book;
    }

    //    @RequestMapping(value = "/books/create",
//            method = RequestMethod.POST)
    @PostMapping(value = "/books/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getDescription());
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping(value = "/books/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id,
                                           @RequestBody Book updatedBook) {
        System.out.println(id);
        System.out.println(updatedBook.getTitle());
        System.out.println(updatedBook.getDescription());
        updatedBook.setId(id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(value = "/books/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        System.out.println(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
