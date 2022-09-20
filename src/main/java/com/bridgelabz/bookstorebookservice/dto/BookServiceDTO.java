package com.bridgelabz.bookstorebookservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Purpose : BookServiceDTO fields are Used to Create and Update Book Details
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */
@Data
public class BookServiceDTO {
    @NotNull(message = "bookName Can't be Null")
    private String bookName;
    @NotNull(message = "author Name Can't be Null")
    private String author;
    @NotNull(message = "bookPrice Can't be Null")
    private long bookPrice;
    @NotNull(message = "bookQuantity Can't be Null")
    private long bookQuantity;
}
