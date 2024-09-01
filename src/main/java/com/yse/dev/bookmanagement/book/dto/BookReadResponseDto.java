package com.yse.dev.bookmanagement.book.dto;

import com.yse.dev.bookmanagement.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BookReadResponseDto {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    public BookReadResponseDto fromBook(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getLocalDateTime();

        return this;
    }

    public static BookReadResponseDto BookFactory(Book book) {
        BookReadResponseDto bookReadResponseDto = new BookReadResponseDto();
        bookReadResponseDto.fromBook(book);
        return bookReadResponseDto;
    }
}
