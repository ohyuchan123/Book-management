package com.yse.dev.bookmanagement.book.dto;

import com.yse.dev.bookmanagement.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookEditResponseDTO {

    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    // 정적 팩토리 메서드로 전환하여 불변성과 가독성을 강화
    public static BookEditResponseDTO fromBook(Book book) {
        BookEditResponseDTO dto = new BookEditResponseDTO();
        dto.bookId = book.getBookId();
        dto.title = book.getTitle();
        dto.price = book.getPrice();
        dto.insertDateTime = book.getLocalDateTime();
        return dto;
    }

    public static BookEditResponseDTO BookFactory(Book book) {
        BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();
        bookEditResponseDTO.fromBook(book);
        return bookEditResponseDTO;
    }

}
