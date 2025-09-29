package com.springbootgamingclubproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springbootgamingclubproject.api.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// Handle resource not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(404).body(ApiResponse.error(ex.getMessage()));
	}

	// Handle insufficient balance
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ApiResponse<?>> handleInsufficientBalance(InsufficientBalanceException ex) {
		return ResponseEntity.badRequest().body(ApiResponse.error(ex.getMessage()));
	}

	// Handle validation errors (first error only)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
		String errorMsg = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).findFirst().orElse("Validation error");
		return ResponseEntity.badRequest().body(ApiResponse.error(errorMsg));
	}

	// Handle generic exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
		return ResponseEntity.status(500).body(ApiResponse.error(ex.getMessage()));
	}

}
