package de.amehlen.booklibrary.exceptions;

import de.amehlen.booklibrary.dto.ErrorDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
