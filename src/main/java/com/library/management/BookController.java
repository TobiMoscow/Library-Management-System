package com.library.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/library")

public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping 
	public List<Book>getBook() {
		return bookService.getBooks();
	}
	
	@PostMapping 
	public void registerNewBook(@RequestBody Book book) {
		bookService.addNewBook(book);
	}

	@DeleteMapping(path = "{bookId}")
	public void deleteBook(@PathVariable Integer bookId) {
		bookService.deleteBook(bookId);
	}
	
	@PutMapping(path = "{bookId}")
	public void updateBook(
			@PathVariable("bookId") Integer bookId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		bookService.updateBook(bookId, name, email);	
	}

}
