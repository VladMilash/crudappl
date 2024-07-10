package com.mvo.crud.exception;

public class CrudException extends RuntimeException {
    public CrudException(String message) {
        super(message);
    }

    public CrudException(String message, Exception e) {
        super(message, e);
    }

    public CrudException(Exception e) {
        super(e);
    }

}
