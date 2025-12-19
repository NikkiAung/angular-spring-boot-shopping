package com.coder.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(CategoryNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubcatNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(SubcatNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ChildcatNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ChildcatNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(TagNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(RoleNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(OrderNotFoundException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FileSaveErrorException.class)
    public ResponseEntity<ErrorResponse> handleException(FileSaveErrorException e) {
        ErrorResponse errResp = new ErrorResponse();
        errResp.setMessage(e.getMessage());
        errResp.setStatus(HttpStatus.NOT_FOUND.value());
        errResp.setTimeSteamp(System.currentTimeMillis());
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }
}
