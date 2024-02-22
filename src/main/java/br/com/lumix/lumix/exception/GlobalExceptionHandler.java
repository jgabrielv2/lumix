package br.com.lumix.lumix.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String>constraintViolationExceptionHandler(ConstraintViolationException ex){
        var errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<String>videoNotFoundExceptionHandler(VideoNotFoundException ex){
        var errorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
