package com.yse.dev.bookmanagement.book.service;

import com.yse.dev.bookmanagement.book.dto.BookCreateDTO;
import com.yse.dev.bookmanagement.book.dto.BookReadResponseDto;
import com.yse.dev.bookmanagement.book.entity.Book;
import com.yse.dev.bookmanagement.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    // NoSuchElementException은 Option 객체에서 값을 가져올 때 값이 존재하지 않은 경우 예외처리를 합니다.
    public BookReadResponseDto read(Integer bookId) throws NoSuchElementException {
        // orElseThrow의 경우 만약 Optional이 값을 포함하고 있으면 그 값을 반환하고, 그렇지 않으면 예외처리 합니다.
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        BookReadResponseDto bookReadResponseDto = new BookReadResponseDto();
        bookReadResponseDto.fromBook(book);
        return bookReadResponseDto;
    }
}
