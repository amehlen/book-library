package de.amehlen.booklibrary.controller;

import de.amehlen.booklibrary.model.Book;
import de.amehlen.booklibrary.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public ResponseEntity<List<Book>> findAllBooks() {
    List<Book> books = bookService.findAllBooks();
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> findBookById(@PathVariable("id") Long id) {
    Book book = bookService.findBookById(id);
    return new ResponseEntity<>(book, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
    Book newBook = bookService.addNewBook(book);
    return new ResponseEntity<>(newBook, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    Book updatedBook = bookService.updateBook(id, book);
    return new ResponseEntity<>(updatedBook, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable("id") Long id) {
    bookService.deleteBook(id);
  }

}
