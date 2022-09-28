package com.bridgelabz.bookstorebookservice.repository;

import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Purpose : BookServiceRepository Are Used to Store the Data into Database
 * Version : 1.0
 * @author : Aviligonda Sreenivasulu
 * */
public interface BookServiceRepository extends JpaRepository<BookServiceModel,Long> {
    List<BookServiceModel> findByBookNameContainsIgnoreCase(String bookName);
    List<BookServiceModel> findByAuthorContainsIgnoreCase(String authorName);
}
