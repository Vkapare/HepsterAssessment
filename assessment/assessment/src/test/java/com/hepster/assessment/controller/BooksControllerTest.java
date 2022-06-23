package com.hepster.assessment.controller;

import com.hepster.assessment.data.Book;
import com.hepster.assessment.service.BooksService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class BooksControllerTest {

    @InjectMocks
    BooksController booksController;
    @Mock
    BooksService booksService;

    @Before("BooksController")
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addBook() {
        Book inputBook = new Book();
        UUID expectedBookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        inputBook.setBookId(expectedBookId);
        inputBook.setActive(true);
        inputBook.setAuthor("Isaac Asimov");
        inputBook.setTitle("Foundation");
        BooksService booksService = Mockito.mock(BooksService.class, Mockito.RETURNS_DEEP_STUBS);
        when(booksService.addBook(inputBook)).thenReturn(UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8"));

        UUID actualBookId = booksService.addBook(inputBook);
        Assertions.assertEquals(expectedBookId, actualBookId);
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

        BooksService booksService = Mockito.mock(BooksService.class, Mockito.RETURNS_DEEP_STUBS);
        when(booksService.addBook(inputBook)).thenReturn(UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8"));

        Book actualBook = booksService.updateBook(bookId, inputBook);
        //Validated the updated field
        Assertions.assertEquals(inputBook.getActive(), actualBook.getActive());
    }

    @Test
    void getAllBooks() {
        List<Book> expectedBookList = new ArrayList<>();
        Book book1 = new Book();
        UUID expectedBookId1 = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        book1.setBookId(expectedBookId1);
        book1.setActive(true);
        book1.setAuthor("Isaac Asimov");
        book1.setTitle("Foundation");
        Book book2 = new Book();
        UUID expectedBookId2 = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a9");
        book2.setBookId(expectedBookId2);
        book2.setActive(false);
        book2.setAuthor("Olaf Stapledon");
        book2.setTitle("Odd John");
        expectedBookList.add(book1);
        expectedBookList.add(book2);
        BooksService booksService = Mockito.mock(BooksService.class, Mockito.RETURNS_DEEP_STUBS);
        when(booksService.getAllBooks()).thenReturn(expectedBookList);

        List<Book> actualBookList = booksService.getAllBooks();
        Assertions.assertEquals(expectedBookList, actualBookList);
    }

    @Test
    void getBooksByBookId() {
        UUID bookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        Book expectedBook = new Book();
        UUID expectedBookId = UUID.fromString("de9dc14a-c86c-4220-9a82-c35f00e950a8");
        expectedBook.setBookId(expectedBookId);
        expectedBook.setActive(true);
        expectedBook.setAuthor("Isaac Asimov");
        expectedBook.setTitle("Foundation");
        BooksService booksService = Mockito.mock(BooksService.class, Mockito.RETURNS_DEEP_STUBS);
        when(booksService.getBooksList(bookId)).thenReturn(expectedBook);

        Book actualBook = booksService.getBooksList(bookId);
        Assertions.assertEquals(expectedBook, actualBook);
    }
}