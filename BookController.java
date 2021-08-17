package com.lara.resourceController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lara.model.Book;
import com.lara.model.SequenceGeneratedService;
import com.lara.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private SequenceGeneratedService sequenceGeneratedService;
	
	@PostMapping("/addbook")
	public Book saveBook(@RequestBody Book book) {
		//generated sequence id....logic here
		book.setId(sequenceGeneratedService.getSequenceNumber(Book.SEQUENCE_NAME));
		return repository.save(book);
		//return "Added with book id:" + book.getId();
	}
	
	@GetMapping("/findallbooks")
	public List<Book> getBooks(){
		return repository.findAll();
	}
	
	@GetMapping("/findbookbyid/{id}")
	public Optional<Book> getBook(@PathVariable int id){
		return repository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "Book deleted by id:" + id;
	}

}
