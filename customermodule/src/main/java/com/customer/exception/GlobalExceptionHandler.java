package com.customer.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.customer.beans.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse> handleOrderNotFoundException(OrderNotFoundException ex) {
    	ApiResponse response = new ApiResponse(ex.getMessage(), 404);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
    	ApiResponse response = new ApiResponse(ex.getMessage(), 404);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ApiResponse> handleInvalidOrderException(InvalidOrderException ex) {
    	ApiResponse response = new ApiResponse(ex.getMessage(), 404);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
    
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	 List<ObjectError> errors = ex.getBindingResult().getAllErrors();
         Map<String, String> map = new HashMap<>();
         errors.forEach((error) -> {
             String key = ((FieldError) error).getField();
             String val = error.getDefaultMessage();
             map.put(key, val);
         });
         
    	ApiResponse response = new ApiResponse(map.toString(), 404);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
    

}
