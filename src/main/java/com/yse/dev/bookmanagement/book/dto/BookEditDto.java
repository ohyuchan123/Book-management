package com.yse.dev.bookmanagement.book.dto;

import com.yse.dev.bookmanagement.book.entity.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookEditDto {

    @NonNull // 필드가 null이 될 수 없음을 나타냅니다.
    @Positive // 필드 값이 양수임을 보장합니다.
    private Integer bookId;

    @NonNull
    @NotBlank // 필드가 null이 아니고 공백이 아닌 문자열임을 보장합니다.
    private String title;

    @NonNull
    @Min(1000)
    private Integer price;

    public Book fill(Book book) {
        book.setTitle(title);
        book.setPrice(price);

        return  book;
    }
}
