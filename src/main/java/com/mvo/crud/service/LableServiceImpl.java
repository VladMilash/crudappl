package com.mvo.crud.service;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Lable;
import com.mvo.crud.repository.LableRepository;

import java.util.List;

public class LableServiceImpl implements LableService {

    private final LableRepository lableRepository;

    public LableServiceImpl(LableRepository lableRepository) {
        this.lableRepository = lableRepository;
    }

    @Override
    public Lable createLable(String name) {
        Lable lable = new Lable(name);
        return lableRepository.save(lable);
    }

    @Override
    public Lable getLableById(Integer id) {
        return lableRepository.findById(id);
    }

    @Override
    public List<Lable> getAllLables() {
        return lableRepository.findAll();
    }

    @Override
    public Lable updateLable(Integer id, String name) {
        Lable updatedLable = lableRepository.findById(id);
        updatedLable.setName(name);
        return lableRepository.update(updatedLable);
    }

    @Override
    public void deleteLableById(Integer id) {
        lableRepository.deleteById(id);
    }
}
