package com.mvo.crud.repository;

import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository <Post, Integer> {
    List<Label> findAllLabelsByPostId(Integer postId);
    void deleteAllLabelsByPostId(Integer postId);
    void addLabelToPost(Integer postId, Integer labelId);
}
