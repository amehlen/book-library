package de.amehlen.booklibrary.exceptions;

import de.amehlen.booklibrary.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleBookNotFoundException(final BookNotFoundException exception) {
    return new ResponseEntity<>(
        ErrorDTO.builder()
            .withTitle("Book Not Found Exception")
            .withMessage(exception.getMessage())
            .withStatus(HttpStatus.NOT_FOUND.value())
            .withErrorType(BookNotFoundException.class.getSimpleName())
            .build(),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(BookAlreadyExistException.class)
  public ResponseEntity<ErrorDTO> handleBookAlreadyExistException(final BookAlreadyExistException exception) {
    return new ResponseEntity<>(
        ErrorDTO.builder()
            .withTitle("Book Already Exist Exception")
            .withMessage(exception.getMessage())
            .withStatus(HttpStatus.CONFLICT.value())
            .withErrorType(BookAlreadyExistException.class.getSimpleName())
            .build(),
        HttpStatus.CONFLICT
    );
  }

}
