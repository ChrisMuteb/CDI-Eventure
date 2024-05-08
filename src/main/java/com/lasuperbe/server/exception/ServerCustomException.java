package com.lasuperbe.server.exception;

import lombok.Data;

@Data
public class ServerCustomException extends RuntimeException{
    private String errorCode;
    public ServerCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
