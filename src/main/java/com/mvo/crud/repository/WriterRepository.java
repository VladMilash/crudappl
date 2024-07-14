package com.mvo.crud.repository;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;

import java.util.List;

public interface WriterRepository extends GenericRepository <Writer, Integer> {
    List<Post> findAllPostsByWriterId(Integer writerId);
    void deleteAllPostsByWriterId(Integer writerId);
    void addPostToWriter(Integer writerId, Integer postId);
}
