package com.yse.dev.bookmanagement.book.service;

import com.yse.dev.bookmanagement.book.dto.BookCreateDTO;
import com.yse.dev.bookmanagement.book.entity.Book;
import com.yse.dev.bookmanagement.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Integer insert(BookCreateDTO bookCreateDTO){
        Book book = Book.builder()
                .title(bookCreateDTO.getTitle())
                .price(bookCreateDTO.getPrice())
                .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }
}
