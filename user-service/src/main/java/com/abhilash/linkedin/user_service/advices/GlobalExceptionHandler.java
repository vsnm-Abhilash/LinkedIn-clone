package com.abhilash.linkedin.user_service.advices;

import io.jsonwebtoken.JwtException;
import com.abhilash.linkedin.user_service.exception.BadCredentialsException;
import com.abhilash.linkedin.user_service.exception.ResourceNotFoundException;
import com.abhilash.linkedin.user_service.exception.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(RuntimeConflictException exception){
        ApiError apiError=ApiError.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.CONFLICT).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException exception){
        ApiError apiError=ApiError.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.UNAUTHORIZED).build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException exception){
        ApiError apiError=ApiError.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.UNAUTHORIZED).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        //return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).
        build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException exception){
        //return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        ApiError apiError=ApiError.builder().status(HttpStatus.FORBIDDEN).message(exception.getMessage()).
                build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(BadCredentialsException exception){
        ApiError apiError=ApiError.builder().status(HttpStatus.BAD_REQUEST).message(exception.getMessage()).
                build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors=exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError=ApiError
                .builder()
                .message("Input Validation failures")
                .subErrors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
