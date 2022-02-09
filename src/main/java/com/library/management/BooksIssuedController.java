package com.library.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/library/booksissued")
public class BooksIssuedController {
	
	@Autowired
	private BooksIssuedService booksissuedService;

	@GetMapping 
	public List<BooksIssued>getBooksIssued() {
		return booksissuedService.getBooksIssued();
	}
	
	@PostMapping 
	public void registerNewBooksIssued(@RequestBody BooksIssued booksissued) {
		booksissuedService.addNewBooksIssued(booksissued);
	}
	
	@DeleteMapping(path = "{booksissuedId}")
	public void deleteBooksIssued(@PathVariable Integer booksissuedId) {
		booksissuedService.deleteBooksIssued(booksissuedId);
	}
	
	@PutMapping(path = "{booksissuedId}")
	public void updateBooksIssued(
			@PathVariable("booksissuedId") Integer booksissuedId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		booksissuedService.updateBooksIssued(booksissuedId, name, email);	
	}
}
