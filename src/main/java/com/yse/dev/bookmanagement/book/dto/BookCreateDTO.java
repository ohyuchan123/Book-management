package com.yse.dev.bookmanagement.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDTO {

    @NonNull
    private String title;

    @NonNull
    private Integer price;
}
