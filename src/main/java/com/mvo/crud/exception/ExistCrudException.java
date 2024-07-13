package com.mvo.crud.exception;

public class ExistCrudException extends CrudException {
    public ExistCrudException() {
        super("There is already exist");
    }
}
