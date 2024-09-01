package com.yse.dev.bookmanagement.book.dto;

import com.yse.dev.bookmanagement.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
// 엔터티를 직접 사용하지 않고 응답 객체를 따로 정의하는 이유는 HTTP 응답이 테이블 하나만 대상으로 하는 경우는
// 거의 없기 때문입니다. 많은 경우 여러 엔터티에서 필요한 필드만 뽑아내서 조합시켜서 응답하기 때문에 응답별로 엔터티
// 집합을 묶어주는 응답 DTO가 필요합니다.
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
