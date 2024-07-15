package com.mvo.crud.service;

import com.mvo.crud.model.Label;
import com.mvo.crud.repository.LabelRepository;

import java.util.List;

public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label createLabel(String name) {
        Label label = new Label(name);
        return labelRepository.save(label);
    }

    @Override
    public Label getLabelById(Integer id) {
        return labelRepository.findById(id);
    }

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @Override
    public Label updateLabels(Integer id, String name) {
        Label updatedLabel = labelRepository.findById(id);
        updatedLabel.setName(name);
        return labelRepository.update(updatedLabel);
    }

    @Override
    public void deleteLabelById(Integer id) {
        labelRepository.deleteById(id);
    }
}
