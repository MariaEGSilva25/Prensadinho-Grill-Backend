package com.unasp.Prensadinho.controller;

import com.unasp.Prensadinho.exceptions.ErrorMessage;
import com.unasp.Prensadinho.exceptions.InvalidStockRangeException;
import com.unasp.Prensadinho.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception){
        ErrorMessage errorMsg = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }
    @ExceptionHandler(InvalidStockRangeException.class)
    public ResponseEntity<String> handleInvalidStockRange(InvalidStockRangeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
