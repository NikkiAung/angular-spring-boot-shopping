package com.coder.shop.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private long timeSteamp;
}
