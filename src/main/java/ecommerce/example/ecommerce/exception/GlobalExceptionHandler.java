package ecommerce.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Utility method to build response body
    private ResponseEntity<Object> buildResponse(Exception ex, WebRequest request, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, status);
    }

    // Handle all uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle insufficient stock
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Object> handleStockException(InsufficientStockException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // Handle product not found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // Handle already exists (e.g., duplicate)
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExistsException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.CONFLICT);
    }

    // Handle generic resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.NOT_FOUND);
    }
}
