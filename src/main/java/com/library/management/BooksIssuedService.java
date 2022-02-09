package com.library.management;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class BooksIssuedService {

	private final BookIssuedRepository bookissuedRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	public BooksIssuedService(BookIssuedRepository bookissuedRepository) {
		this.bookissuedRepository = bookissuedRepository;
	}

	public List<BooksIssued>getBooksIssued() {
		return bookissuedRepository.findAll();
	}

	public void addNewBooksIssued(BooksIssued booksissued) {
		bookissuedRepository.save(booksissued);
	}
	
	
	public void deleteBook(Integer booksIssuedId) {
		boolean exists = bookissuedRepository.existsById(booksIssuedId);
		if(!exists) {
			throw new IllegalStateException(
					"booksissued with id  " + booksIssuedId + " does not exists");
		}
		bookissuedRepository.deleteById(booksIssuedId);
	}
	
	@Transactional
	public void updateBooksIssued(Integer booksIssuedId, int userId, int bookId, String status) {
		BooksIssued booksissued = bookissuedRepository.findById(booksIssuedId)
				.orElseThrow(() -> new IllegalStateException("booksissued with id " + booksIssuedId + " does not exist"));
		Optional<User> userById = userService.getUserById(userId);
		if(userById.isPresent()) {
				booksissued.setUser_id(userId);
		}else {
			new IllegalStateException("user with id " + userId + " does not exist");
		}
		Optional<Book> bookById = bookService.getBookById(bookId);
		if(bookById.isPresent()) {
			booksissued.setBook_id(bookId);
		}else {
			new IllegalStateException("book with id " + bookId + " does not exist");
		}
		if(status != null && status.length() > 0 && !Objects.equals(booksissued.getStatus(), status)) {
			booksissued.setStatus(status);
		}
		bookissuedRepository.save(booksissued);
	}

	public void deleteBooksIssued(Integer booksissuedId) {
		boolean exists = bookissuedRepository.existsById(booksissuedId);
		if(!exists) {
			throw new IllegalStateException(
					"booksissued with id  " + booksissuedId + " does not exists");
		}
		bookissuedRepository.deleteById(booksissuedId);
		
		}

	public void updateBooksIssued(Integer booksissuedId, String name, String email) {
		// TODO Auto-generated method stub
		
	}
	}	
