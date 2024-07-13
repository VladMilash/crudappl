package com.mvo.crud.service;

import com.mvo.crud.model.Lable;

import java.util.List;

public interface LableService {
    Lable createLable(String name);

    Lable getLableById(Integer id);

    List<Lable> getAllLables();

    Lable updateLable(Integer id, String name);

    void deleteLableById(Integer id);
}
