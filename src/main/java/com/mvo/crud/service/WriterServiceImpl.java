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
        writerRepository.save(writer);
        return writer;
    }

    @Override
    public Writer getWriterById(Integer id) {
        Writer writer = writerRepository.findById(id);
        if (writer == null) {
            throw new NotExistCrudException(id);
        }
        return writer;
    }

    @Override
    public List<Writer> getAllWriters() {
        try {
            return writerRepository.findAll();
        } catch (CrudException e) {
            throw new CrudException(e);
        }
    }

    @Override
    public Writer updateWriter(Integer id, String firstName, String lastName) {
        Writer existingWriter = getWriterById(id);
        existingWriter.setFirstName(firstName);
        existingWriter.setLastName(lastName);
        writerRepository.update(existingWriter);
        return existingWriter;
    }

    @Override
    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }
}
