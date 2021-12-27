package com.starwars.handler;

import com.starwars.handler.exception.ApiError;
import com.starwars.handler.exception.RebeldeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
  private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
  private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

  @ExceptionHandler(RebeldeNotFoundException.class)
  public ResponseEntity<ApiError> handlerAccessDeniedException(RebeldeNotFoundException exception) {
    return ResponseEntity.status(NOT_FOUND)
        .body(createResponseException(exception.getMessage(), NOT_FOUND.value(), LocalDate.now()));
  }

  private ApiError createResponseException(String message, int statusCode, LocalDate date) {
    return new ApiError(message, statusCode, date);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<String> errors = new ArrayList<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              errors.add(error.getDefaultMessage());
            });

    return ResponseEntity.status(BAD_REQUEST)
        .body(createResponseException(errors.toString(), BAD_REQUEST.value(), LocalDate.now()));
  }
}
