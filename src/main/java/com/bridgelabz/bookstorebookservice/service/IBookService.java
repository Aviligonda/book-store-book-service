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

    Response updateBook(Long id, String token, BookServiceDTO bookServiceDTO);

    List<BookServiceModel> getAllBooks(String token);

    Response deleteBook(Long id, String token);

    Response changeBookQuantity(Long id, String token, Long quantity);

    Response changeBookPrice(Long id, String token, Long price);
}
