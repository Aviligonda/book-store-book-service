package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.exception.UserException;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.repository.BookServiceRepository;
import com.bridgelabz.bookstorebookservice.util.CartResponse;
import com.bridgelabz.bookstorebookservice.util.Response;
import com.bridgelabz.bookstorebookservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose : BookService to Implement the Business Logic
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */

@Service
public class BookService implements IBookService {
    @Autowired
    BookServiceRepository bookServiceRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose : Implement the Logic of Creating Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  bookServiceDTO,token
     */
    @Override
    public Response createBook(String token, BookServiceDTO bookServiceDTO) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8081/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            BookServiceModel bookServiceModel = new BookServiceModel(bookServiceDTO);
            bookServiceModel.setCreationTime(LocalDateTime.now());
            bookServiceRepository.save(bookServiceModel);
            return new Response(200, "Success", bookServiceModel);
        }
        return null;
    }

    /**
     * Purpose : Implement the Logic of Update Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  bookServiceDTO,token,id
     */
    @Override
    public Response updateBook(Long id, String token, Long bookQuantity) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
            if (isBookPresent.isPresent()) {
                Long quantity = isBookPresent.get().getBookQuantity() + bookQuantity;
                isBookPresent.get().setBookQuantity(quantity);
                isBookPresent.get().setUpdatedTime(LocalDateTime.now());
                bookServiceRepository.save(isBookPresent.get());
                return new Response(200, "Success", isBookPresent.get());
            }
            throw new UserException(400, "Book Is Not Found");
        }
        return null;
    }

    /**
     * Purpose : Implement the Logic of Get All Books
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  token
     */
    @Override
    public List<BookServiceModel> getAllBooks(String token) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            List<BookServiceModel> isBooksPresnt = bookServiceRepository.findAll();
            if (isBooksPresnt.size() > 0) {
                return isBooksPresnt;
            }
            throw new UserException(400, "No Books Found");
        }
        return null;
    }

    /**
     * Purpose : Implement the Logic of Delete Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  userServiceDTO
     */
    @Override
    public Response deleteBook(Long id, String token) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
            if (isBookPresent.isPresent()) {
                bookServiceRepository.delete(isBookPresent.get());
                return new Response(200, "Success", isBookPresent.get());
            }
            throw new UserException(400, "No Book Found with this ID");
        }
        return null;
    }

    /**
     * Purpose : Implement the Logic of changeBookPrice
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  price,id,token
     */
    @Override
    public Response changeBookPrice(Long id, String token, Long price) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
            if (isBookPresent.isPresent()) {
                isBookPresent.get().setBookPrice(price);
                bookServiceRepository.save(isBookPresent.get());
                return new Response(200, "Success", isBookPresent.get());
            }
            throw new UserException(400, "No Book Found with this ID");
        }
        return null;
    }

    /**
     * Purpose : Implement the Logic of verify Book
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  id
     */
    @Override
    public Response verifyBook(Long id) {
        Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
        if (isBookPresent.isPresent()) {
            return new Response(200, "Book Found", isBookPresent.get());
        }
        throw new UserException(400, "Book NOt found With this id");
    }

    /**
     * Purpose : Implement the Logic of changeBookQuantity when add book in cart
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  id,bookId
     */

    @Override
    public Response changeBookQuantity(Long quantity, Long bookId) {
        Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(bookId);
        if (isBookPresent.isPresent()) {
            Long bookQuantity = isBookPresent.get().getBookQuantity() - quantity;
            isBookPresent.get().setBookQuantity(bookQuantity);
            bookServiceRepository.save(isBookPresent.get());
            return new Response(200, "Success", isBookPresent.get());
        }
        throw new UserException(400, "No Book Found With This ID");
    }

    /**
     * Purpose : Implement the Logic of changeBookQuantity when removing book in cart
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  id,bookId
     */
    @Override
    public Response changeBookQuantity1(Long quantity, Long bookId) {
        Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(bookId);
        if (isBookPresent.isPresent()) {
            Long bookQuantity = isBookPresent.get().getBookQuantity() + quantity;
            isBookPresent.get().setBookQuantity(bookQuantity);
            bookServiceRepository.save(isBookPresent.get());
            return new Response(200, "Success", isBookPresent.get());
        }
        throw new UserException(400, "No Book Found With This ID");
    }
}