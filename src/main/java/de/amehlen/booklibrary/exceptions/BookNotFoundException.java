package de.amehlen.booklibrary.exceptions;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(String message) {
    super(message);
  }

}
