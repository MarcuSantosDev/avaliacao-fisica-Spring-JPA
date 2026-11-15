package com.marcusantosproject.avaliacaofisica.handler;

import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        StandardError err = StandardError.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error("Validation Error")
                .mensage(message)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> NotFoundException(
            NotFoundException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = StandardError.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error("Not Found Error")
                .mensage(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> BadRequestException(
            BadRequestException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = StandardError.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error("Bad Request")
                .mensage(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(err);
    }
}