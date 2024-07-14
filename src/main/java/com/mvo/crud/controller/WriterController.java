package com.mvo.crud.controller;

public interface WriterController {
    void createWriter();

    void getWriterById();

    void getAllWriters();

    void updateWriter();

    void deleteWriterById();

    void findAllPostsByWriterId();

    void deleteAllPostsByWriterId();

    void addPostToWriter();

    void closeScanner();
}
