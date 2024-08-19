package com.yse.dev.bookmanagement.book.repository;

import com.yse.dev.bookmanagement.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
