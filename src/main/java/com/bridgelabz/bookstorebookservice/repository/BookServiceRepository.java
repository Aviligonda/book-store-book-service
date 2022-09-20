package com.bridgelabz.bookstorebookservice.repository;

import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Purpose : BookServiceRepository Are Used to Store the Data into Database
 * Version : 1.0
 * @author : Aviligonda Sreenivasulu
 * */
public interface BookServiceRepository extends JpaRepository<BookServiceModel,Long> {
}
