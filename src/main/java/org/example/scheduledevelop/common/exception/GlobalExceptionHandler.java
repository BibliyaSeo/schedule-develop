package org.example.scheduledevelop.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<ErrorResponse> handleCustom(MyCustomException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                ex.getCode(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }

    // Validation 체크
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        FieldError firstError = ex.getBindingResult().getFieldErrors().get(0);
        String message = String.format("[%s] %s", firstError.getField(), firstError.getDefaultMessage());
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(error);
    }
}