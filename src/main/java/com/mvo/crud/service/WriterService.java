package com.mvo.crud.service;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;

import java.util.List;

public interface WriterService {

    Writer createWriter(String firstName, String lastName);

    Writer getWriterById(Integer id);

    List<Writer> getAllWriters();

    Writer updateWriter(Integer id, String firstName, String lastName);

    void deleteWriterById(Integer id);
}