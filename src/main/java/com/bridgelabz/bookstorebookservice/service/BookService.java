package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.exception.UserException;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.repository.BookServiceRepository;
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
    public Response updateBook(Long id, String token, BookServiceDTO bookServiceDTO) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
            if (isBookPresent.isPresent()) {
                isBookPresent.get().setBookName(bookServiceDTO.getBookName());
                isBookPresent.get().setAuthor(bookServiceDTO.getAuthor());
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
     * Purpose : Implement the Logic of changeBookQuantity
     *
     * @author : Aviligonda Sreenivasulu
     * @Param :  quantity,id,token
     */
    @Override
    public Response changeBookQuantity(Long id, String token, Long quantity) {
        Response isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8080/userService/userVerification/" + token, Response.class);
        if (isUserPresent.getStatusCode() == 200) {
            Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(id);
            if (isBookPresent.isPresent()) {
                isBookPresent.get().setBookQuantity(quantity);
                bookServiceRepository.save(isBookPresent.get());
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
}