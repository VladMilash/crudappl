package com.mvo.crud.service;

import com.mvo.crud.model.Label;

import java.util.List;

public interface LableService {
    Label createLable(String name);

    Label getLableById(Integer id);

    List<Label> getAllLables();

    Label updateLable(Integer id, String name);

    void deleteLableById(Integer id);
}
