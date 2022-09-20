package com.bridgelabz.bookstorebookservice.model;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 * Purpose : BookServiceModel Are Used Create A table and connection to Database
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */
@Data
@Entity
@Table(name = "books")
public class BookServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String author;
    @Lob
    private byte[] logo;
    private long bookPrice;
    private long bookQuantity;
    private LocalDateTime creationTime;
    private LocalDateTime updatedTime;

    public BookServiceModel(BookServiceDTO bookServiceDTO) {
        this.author = bookServiceDTO.getAuthor();
        this.bookName = bookServiceDTO.getBookName();
        this.bookPrice = bookServiceDTO.getBookPrice();
        this.bookQuantity = bookServiceDTO.getBookQuantity();
    }

    public BookServiceModel() {
    }
}
