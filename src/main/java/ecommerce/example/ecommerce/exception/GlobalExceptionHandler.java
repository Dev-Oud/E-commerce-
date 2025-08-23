package ecommerce.example.ecommerce.exception;

import ecommerce.example.ecommerce.response.ApiResponse;
import ecommerce.example.ecommerce.dto.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<AuthResponse> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        AuthResponse authResponse = new AuthResponse(ex.getMessage(), null);
        return new ResponseEntity<>(authResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse("Access Denied: You do not have permission to perform this action.",
                null);
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ApiResponse apiResponse = new ApiResponse("An internal server error occurred. Please contact support.", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ApiResponse> handleInsufficientStockException(InsufficientStockException ex,
            WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}