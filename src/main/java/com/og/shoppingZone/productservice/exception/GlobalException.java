package com.og.shoppingZone.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.og.shoppingZone.productservice.entity.Product;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> notFound(ProductNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidProductException.class)
	public ResponseEntity<String> notFound(InvalidProductException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
