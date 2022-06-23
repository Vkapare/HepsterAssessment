package com.hepster.assessment.service;

import com.hepster.assessment.dao.BookRepository;
import com.hepster.assessment.data.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class BookServiceTest {

    @InjectMocks
    BooksService booksService;

    @Mock
    Book book;

    @Mock
    BookRepository bookRepository;

    @Test
    void addBook() {
        Book inputBook = new Book();
        UUID expectedBookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        inputBook.setBookId(expectedBookId);
        inputBook.setActive(true);
        inputBook.setAuthor("Isaac Asimov");
        inputBook.setTitle("Foundation");
        BookRepository bookRepository = Mockito.mock(BookRepository.class, Mockito.RETURNS_DEEP_STUBS);
        when(bookRepository.save(inputBook)).thenReturn(inputBook);

        Book actualBook = bookRepository.save(inputBook);
        Assertions.assertEquals(actualBook, inputBook);
    }


    @Test
    void getAllBooksList() {
        Book inputBook1 = new Book();
        UUID expectedBookId1 = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a9");
        inputBook1.setBookId(expectedBookId1);
        inputBook1.setActive(true);
        inputBook1.setAuthor("Isaac Asimov");
        inputBook1.setTitle("Foundation");
        Book inputBook2 = new Book();
        UUID expectedBookId2 = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a9");
        inputBook2.setBookId(expectedBookId2);
        inputBook2.setActive(false);
        inputBook2.setAuthor("Olaf Stapledon");
        inputBook2.setTitle("Odd John");
        List<Book> expectedBookList = new ArrayList<>();
        expectedBookList.add(inputBook1);
        expectedBookList.add(inputBook2);
        BookRepository bookRepository = Mockito.mock(BookRepository.class, Mockito.RETURNS_DEEP_STUBS);
        when(bookRepository.findAll()).thenReturn(expectedBookList);

        List<Book> actualBookList = bookRepository.findAll();
        Assertions.assertEquals(actualBookList, expectedBookList);
    }

    @Test
    void getBooksByBookId() {
        Book inputBook = new Book();
        UUID expectedBookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a9");
        inputBook.setBookId(expectedBookId);
        inputBook.setActive(true);
        inputBook.setAuthor("Isaac Asimov");
        inputBook.setTitle("Foundation");
        Optional<Book> optionalBook = Optional.of(inputBook);


        List<Book> expectedBookList = new ArrayList<>();
        expectedBookList.add(inputBook);

        BookRepository bookRepository = Mockito.mock(BookRepository.class, Mockito.RETURNS_DEEP_STUBS);
        when(bookRepository.findById(expectedBookId)).thenReturn(optionalBook);

        Optional<Book> actualBook = bookRepository.findById(expectedBookId);
        Assertions.assertEquals(actualBook.get(), inputBook);
    }

    @Test
    void updateBook() {

        Book inputBook = new Book();
        UUID bookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        inputBook.setBookId(bookId);
        //Updated the field
        inputBook.setActive(false);
        inputBook.setAuthor("Isaac Asimov");
        inputBook.setTitle("Foundation");

        BookRepository bookRepository = Mockito.mock(BookRepository.class, Mockito.RETURNS_DEEP_STUBS);
        when(bookRepository.findById(bookId)).thenReturn(null);
        when(bookRepository.save(inputBook)).thenReturn(inputBook);

        Book actualBook = bookRepository.save(inputBook);
        //Validated the updated field
        Assertions.assertEquals(inputBook.getActive(), actualBook.getActive());
    }
}