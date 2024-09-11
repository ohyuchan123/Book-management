package com.yse.dev.bookmanagement.book.service;

import com.yse.dev.bookmanagement.book.dto.BookCreateDTO;
import com.yse.dev.bookmanagement.book.dto.BookEditDto;
import com.yse.dev.bookmanagement.book.dto.BookEditResponseDTO;
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
    public BookEditResponseDTO read(Integer bookId){
        return bookRepository.findById(bookId)
                .map(BookEditResponseDTO::fromBook)
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + bookId));
        // Optional의 map 메서드를 사용하여 코드 간소화
    }

    public BookEditResponseDTO edit(Integer bookId) {
        return bookRepository.findById(bookId)
                .map(BookEditResponseDTO::BookFactory)
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + bookId));
        // 동일한 패턴을 적용하여 Optional의 map 메서드 사용
    }

    public void update(BookEditDto bookEditDto) {
        Book book = bookRepository.findById(bookEditDto.getBookId())
                .map(existingBook -> bookEditDto.fill(existingBook))
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + bookEditDto.getBookId()));

        bookRepository.save(book); // 수정된 책 정보를 저장
    }
}
