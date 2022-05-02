package de.amehlen.booklibrary.controller;

import de.amehlen.booklibrary.model.Book;
import de.amehlen.booklibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "books", description = "Endpoints for getting and manipulating books")
@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @Operation(
      summary = "Get a list of all books",
      description = "Get a list of all books saved in the database",
      tags = {"books"},
      responses = {
          @ApiResponse(
              description = "List of all books",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class)
              )
          )
      }
  )
  @GetMapping
  public ResponseEntity<List<Book>> findAllBooks() {
    List<Book> books = bookService.findAllBooks();
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @Operation(
      summary = "Get a selected book by id",
      description = "Get a selected book by id from the database",
      tags = {"books"},
      responses = {
          @ApiResponse(
              description = "Selected book",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class)
              )
          )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<Book> findBookById(@PathVariable("id") Long id) {
    Book book = bookService.findBookById(id);
    return new ResponseEntity<>(book, HttpStatus.OK);
  }

  @Operation(
      summary = "Add a new book",
      description = "Add a new book to the database",
      tags = {"books"},
      responses = {
          @ApiResponse(
              description = "New added book",
              responseCode = "201",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class)
              )
          )
      }
  )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Book> addNewBook(@Valid @RequestBody Book book) {
    Book newBook = bookService.addNewBook(book);
    return new ResponseEntity<>(newBook, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Update a selected book",
      description = "Update a selected book in the database",
      tags = {"books"},
      responses = {
          @ApiResponse(
              description = "Updated book",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class)
              )
          )
      }
  )
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    Book updatedBook = bookService.updateBook(id, book);
    return new ResponseEntity<>(updatedBook, HttpStatus.OK);
  }

  @Operation(
      summary = "Delete a selected book",
      description = "Delete a selected book in the database",
      tags = {"books"}
  )
  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable("id") Long id) {
    bookService.deleteBook(id);
  }

}
