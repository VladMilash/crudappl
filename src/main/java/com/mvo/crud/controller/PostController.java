package com.mvo.crud.controller;

import com.mvo.crud.model.Label;

import java.util.List;

public interface PostController {
    void createPost();

    void getPostById();

    void getAllPosts();

    void updatePost();

    void deletePostById();

    void closeScanner();

    void findAllLabelsByPostId();

    void deleteAllLabelsByPostId();

    void addLabelToPost();
}
