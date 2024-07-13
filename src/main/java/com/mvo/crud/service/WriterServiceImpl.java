package com.mvo.crud.service;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;

import java.util.List;

public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer createWriter(String firstName, String lastName) {
        Writer writer = new Writer(firstName, lastName);
        return writerRepository.save(writer);
    }

    @Override
    public Writer getWriterById(Integer id) {
        return writerRepository.findById(id);
    }

    @Override
    public List<Writer> getAllWriters() {
        return writerRepository.findAll();
    }

    @Override
    public Writer updateWriter(Integer id, String firstName, String lastName) {
        Writer existingWriter = getWriterById(id);
        existingWriter.setFirstName(firstName);
        existingWriter.setLastName(lastName);
        return writerRepository.update(existingWriter);
    }

    @Override
    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }
}
