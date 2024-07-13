package com.mvo.crud.controller;

public interface PostController {
    void createPost();

    void getPostById();

    void getAllPosts();

    void updatePost();

    void deletePostById();

    void closeScanner();
}
