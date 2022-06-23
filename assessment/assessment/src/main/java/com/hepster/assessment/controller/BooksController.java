package com.hepster.assessment.controller;

import com.hepster.assessment.data.Book;
import com.hepster.assessment.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class BooksController {


    @Autowired
    BooksService booksService;

    @PostMapping(value = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> addBook(@RequestBody Book book){
        return new ResponseEntity<> (booksService.addBook(book), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id", required = true)UUID bookId,
                                           @RequestBody Book book){

        if (booksService.updateBook(bookId, book)!= null)
            return new ResponseEntity<>(booksService.updateBook(bookId, book), HttpStatus.OK);
        else
            return new ResponseEntity<>(booksService.updateBook(bookId, book), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks(){
        return booksService.getAllBooks();
    }

    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBooks(@PathVariable(value = "id", required = true)UUID bookId){
        return booksService.getBooksList(bookId);
    }
}
