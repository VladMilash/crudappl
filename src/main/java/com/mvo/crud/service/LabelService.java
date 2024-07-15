package com.mvo.crud.service;

import com.mvo.crud.model.Label;

import java.util.List;

public interface LabelService {
    Label createLabel(String name);

    Label getLabelById(Integer id);

    List<Label> getAllLabels();

    Label updateLabels(Integer id, String name);

    void deleteLabelById(Integer id);
}
