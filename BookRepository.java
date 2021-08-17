package com.lara.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lara.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
