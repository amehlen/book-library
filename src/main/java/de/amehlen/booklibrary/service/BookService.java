package de.amehlen.booklibrary.service;

import de.amehlen.booklibrary.exceptions.BookAlreadyExistException;
import de.amehlen.booklibrary.exceptions.BookNotFoundException;
import de.amehlen.booklibrary.model.Book;
import de.amehlen.booklibrary.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Book findBookById(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found."));
  }

  public Book addNewBook(Book book) {
    Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());
    if (bookOptional.isPresent()) {
      throw new BookAlreadyExistException("Book with isbn " + book.getIsbn() + " already exist.");
    }
    return bookRepository.save(book);
  }

  public Book updateBook(Long id, Book book) {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new BookNotFoundException("Book with id " + id + " not found.");
    }
    Book bookFound = bookOptional.get();
    bookFound.setIsbn(book.getIsbn());
    bookFound.setTitle(book.getTitle());
    bookFound.setAuthor(book.getAuthor());
    bookFound.setPublisher(book.getPublisher());
    bookFound.setPublicationYear(book.getPublicationYear());
    bookFound.setEdition(book.getEdition());
    bookFound.setDescription(book.getDescription());
    bookFound.setPages(book.getPages());
    return bookRepository.save(bookFound);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

}
