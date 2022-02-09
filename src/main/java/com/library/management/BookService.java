package com.library.management;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class BookService {

private final BookRepository bookRepository;
	
	@Autowired 
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book>getBooks() {
		return bookRepository.findAll();
	}
	
	public void addNewBook(Book book) {
		Optional<Book>bookOptional = bookRepository.findBookByTitle(book.getTitle());
		if(bookOptional.isPresent()) {
			throw new IllegalStateException("title taken");
		}
		bookRepository.save(book);
	}
	public void deleteBook(Integer bookId) {
		boolean exists = bookRepository.existsById(bookId);
		if(!exists) {
			throw new IllegalStateException(
					"book with id  " + bookId + " does not exists");
		}
		bookRepository.deleteById(bookId);
	}
	
	@Transactional
	public void updateBook(Integer bookId, String title, String author) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new IllegalStateException(
						"book with id " + bookId + " does not exist"));
				if(title != null &&
						title.length() > 0 &&
						!Objects.equals(book.getTitle(), title)) {
					book.setTitle(title);
				}
				if(author != null &&
						author.length() > 0 &&
						!Objects.equals(book.getAuthor(), author)) {
					Optional<Book> bookOptional = bookRepository
							.findBookByAuthor(author);
					if (bookOptional.isPresent()) {
						throw new IllegalStateException("author taken");
					}
					book.setAuthor(author);
					}
				}
		public Optional<Book> getBookById(Integer id){
				return bookRepository.findById(id);
			}
		}

