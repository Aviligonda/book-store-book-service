package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.util.Response;

import java.util.List;

/**
 * Purpose : IUserService to Show The all APIs
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */
public interface IBookService {
    Response createBook(String token, BookServiceDTO bookServiceDTO);

    Response updateBook(Long id, String token, Long bookQuantity);

    List<BookServiceModel> getAllBooks(String token);

    Response deleteBook(Long id, String token);

    Response changeBookPrice(Long id, String token, Long price);

    Response verifyBook(Long id);

    Response changeBookQuantity(Long quantity, Long bookId);

    Response changeBookQuantity1(Long quantity, Long bookId);

    List<BookServiceModel> searchBookByAuthor(String authorName, String token);

    List<BookServiceModel> searchByBookName(String bookName, String token);
}
