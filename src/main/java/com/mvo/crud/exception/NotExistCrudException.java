package com.mvo.crud.exception;

public class NotExistCrudException extends CrudException {
    public NotExistCrudException(int id) {
        super("Entity with id " + id + " not found");
    }
}
