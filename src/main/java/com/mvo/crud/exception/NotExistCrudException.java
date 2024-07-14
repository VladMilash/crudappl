package com.mvo.crud.exception;

public class NotExistCrudException extends CrudException {
    public NotExistCrudException(int id) {
        super("Entity with id " + id + " not found");
    }

    public NotExistCrudException(int writerId, int postId) {
        super("No writer or post found with: " + " writer ID: " + writerId + " post ID " + postId);
    }
}
