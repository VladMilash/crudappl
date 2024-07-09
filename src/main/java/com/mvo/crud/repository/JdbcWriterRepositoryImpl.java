package com.mvo.crud.repository;

import com.mvo.crud.model.Writer;

import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer findById(Integer integer) {
        return null;
    }

    @Override
    public List<Writer> findAll() {
        return List.of();
    }

    @Override
    public void save(Writer entity) {

    }

    @Override
    public void update(Writer entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
