package com.mvo.crud.exception;

public class NotExistCrudException extends CrudException {
    public NotExistCrudException(int id) {
        super("Entity with id " + id + " not found");
    }

    public NotExistCrudException(int firstEntityId, int secondEntityId) {
        super("No first entity or second entity not found with: " + " first entity Id,: " + firstEntityId + " post ID " + secondEntityId);
    }
}
