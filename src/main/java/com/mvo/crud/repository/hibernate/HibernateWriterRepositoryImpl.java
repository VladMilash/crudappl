package com.mvo.crud.repository.hibernate;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {
    @Override
    public List<Post> findAllPostsByWriterId(Integer writerId) {
        return List.of();
    }

    @Override
    public void deleteAllPostsByWriterId(Integer writerId) {

    }

    @Override
    public void addPostToWriter(Integer writerId, Integer postId) {

    }

    @Override
    public Writer findById(Integer integer) {
        return null;
    }

    @Override
    public List<Writer> findAll() {
        return List.of();
    }

    @Override
    public Writer save(Writer entity) {
        return null;
    }

    @Override
    public Writer update(Writer entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
