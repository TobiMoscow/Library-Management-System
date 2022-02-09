package com.library.management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

	Optional<Book> findBookByTitle(String title);

	Optional<Book> findBookByAuthor(String author); 

}
