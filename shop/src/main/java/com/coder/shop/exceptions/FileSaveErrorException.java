package com.coder.shop.exceptions;

public class FileSaveErrorException extends RuntimeException{
    public FileSaveErrorException(String message) {
        super(message);
    }
}
