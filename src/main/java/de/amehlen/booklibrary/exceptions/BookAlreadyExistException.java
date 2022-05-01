package de.amehlen.booklibrary.exceptions;

public class BookAlreadyExistException extends RuntimeException {

  public BookAlreadyExistException(String message) {
    super(message);
  }

}
