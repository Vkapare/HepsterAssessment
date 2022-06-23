package com.hepster.assessment.service;


import com.hepster.assessment.dao.BookRepository;
import com.hepster.assessment.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BooksService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public UUID addBook(Book book) {
        if (null == book){
            throw new RuntimeException("Books cannot be null");
        }
        return bookRepository.save(book).getBookId();
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBooksList(UUID booksId){
        Optional<Book> book = bookRepository.findById(booksId);
        return book.isPresent()?book.get():new Book();
    }


    @Transactional
    public Book updateBook(UUID bookId, Book book){
        if(bookRepository.findById(bookId)!= null){
            return bookRepository.save(book);
        }else
            return null;
    }
}
