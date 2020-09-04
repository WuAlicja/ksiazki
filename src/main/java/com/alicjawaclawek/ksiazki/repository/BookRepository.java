package com.alicjawaclawek.ksiazki.repository;

import com.alicjawaclawek.ksiazki.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {

}
