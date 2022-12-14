package com.bridgelabz.bookstorebookservice.controller;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.service.IBookService;
import com.bridgelabz.bookstorebookservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose :REST ApIs Controller
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */
@RestController
@RequestMapping("/bookService")
public class BookServiceController {
    @Autowired
    IBookService bookService;

    /**
     * Purpose :  Create Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : token,bookServiceDTO
     */
    @PostMapping("/createBook")
    public ResponseEntity<Response> createBook(@RequestHeader String token,
                                               @Valid @RequestBody BookServiceDTO bookServiceDTO) {
        Response response = bookService.createBook(token, bookServiceDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose :  Update Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : token,bookServiceDTO,id
     */
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Response> updateBook(@PathVariable Long id,
                                               @RequestHeader String token,
                                               @RequestParam Long bookQuantity) {
        Response response = bookService.updateBook(id, token, bookQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose :  Get All Books
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : token
     */
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<?>> getAllBooks(@RequestHeader String token) {
        List<BookServiceModel> response = bookService.getAllBooks(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose :  Delete Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : token,id
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable Long id,
                                               @RequestHeader String token) {
        Response response = bookService.deleteBook(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose :  Change Book price
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : token,price,id
     */
    @PutMapping("/changeBookPrice/{id}")
    public ResponseEntity<Response> changeBookPrice(@PathVariable Long id,
                                                    @RequestHeader String token,
                                                    @RequestParam Long price) {
        Response response = bookService.changeBookPrice(id, token, price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose :  Verify Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : id
     */
    @GetMapping("verifyBook/{id}")
    public Response verifyBook(@PathVariable Long id) {
        return bookService.verifyBook(id);
    }

    /**
     * Purpose :  changeBookQuantity when Add book in cart
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : bookId,quantity
     */
    @GetMapping("/changeBookQuantity/{quantity}/{bookId}")
    public Response changeBookQuantity(@PathVariable Long quantity,
                                       @PathVariable Long bookId) {
        return bookService.changeBookQuantity(quantity, bookId);
    }

    /**
     * Purpose :  changeBookQuantity when removing book in cart
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : bookId,quantity
     */
    @GetMapping("/changeBookQuantity1/{quantity}/{bookId}")
    public Response changeBookQuantity1(@PathVariable Long quantity,
                                        @PathVariable Long bookId) {
        return bookService.changeBookQuantity1(quantity, bookId);

    }

    /**
     * Purpose : search By BookName
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : bookName
     */
    @GetMapping("/searchByBookName/{bookName}")
    public ResponseEntity<List<?>> searchByBookName(@PathVariable String bookName,
                                                    @RequestHeader String token) {
        List<BookServiceModel> response = bookService.searchByBookName(bookName,token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose : search a Book by author name
     *
     * @author : Aviligonda Sreenivasulu
     * @Param : authorName
     */
    @GetMapping("/searchBookByAuthor/{authorName}")
    public ResponseEntity<List<?>> searchBookByAuthor(@PathVariable String authorName,
                                                      @RequestHeader String token) {
        List<BookServiceModel> response = bookService.searchBookByAuthor(authorName,token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
