package com.mvo.crud.exception;

public class CrudException extends RuntimeException {
    public CrudException(String message) {
        super(message);
    }

    public CrudException(Exception e) {
        super(e);
    }

}
