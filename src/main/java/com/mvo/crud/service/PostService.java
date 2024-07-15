package com.mvo.crud.service;

import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;

import java.util.List;

public interface PostService {
    Post createPost(PostStatus postStatus, String content);

    Post getPostById(Integer id);

    List<Post> getAllPosts();

    Post updatePost(Integer id, PostStatus postStatus, String content);

    void deletePostById(Integer id);

    List<Label> findAllLabelsByPostId(Integer postId);

    void deleteAllLabelsByPostId(Integer postId);

    void addLabelToPost(Integer postId, Integer labelId);
}
