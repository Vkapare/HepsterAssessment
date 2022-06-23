package com.hepster.assessment.dao;

import com.hepster.assessment.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    //Book findByBookId(UUID booksId);
}
