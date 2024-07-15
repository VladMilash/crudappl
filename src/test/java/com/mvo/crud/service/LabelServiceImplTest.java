package com.mvo.crud.service;

import com.mvo.crud.model.Label;
import com.mvo.crud.repository.LabelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LabelServiceImplTest {

    @Mock
    private LabelRepository labelRepository;

    private LabelService labelService;

    private Label testLabel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        labelService = new LabelServiceImpl(labelRepository);
        testLabel = new Label(1, "Test");
    }

    @Test
    public void createLabel() {
        when(labelRepository.save(any(Label.class))).thenReturn(testLabel);
        Label createdLabel = labelService.createLabel("Test");
        assertEquals(testLabel, createdLabel);
        verify(labelRepository, times(1)).save(any(Label.class));
    }

    @Test
    public void getLabelById() {
        when(labelRepository.findById(any(Integer.class))).thenReturn(testLabel);
        Label findingLabel = labelService.getLabelById(1);
        assertEquals(testLabel, findingLabel);
        verify(labelRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    public void getAllLabels() {
        List<Label> labels = new ArrayList<>();
        labels.add(testLabel);
        when(labelRepository.findAll()).thenReturn(labels);
        List<Label> findinglabels = labelService.getAllLabels();
        assertEquals(1, findinglabels.size());
        assertEquals(labels, findinglabels);
        verify(labelRepository, times(1)).findAll();
    }

    @Test
    public void updateLabel() {
        when(labelRepository.findById(1)).thenReturn(testLabel);
        when(labelRepository.update(any(Label.class))).thenReturn(testLabel);
        Label createdLabel = labelService.updateLabels(1, "Test");
        assertEquals(testLabel, createdLabel);
        verify(labelRepository, times(1)).update(any(Label.class));
        verify(labelRepository, times(1)).findById(1);
    }

    @Test
    public void deleteLabelById() {
        labelService.deleteLabelById(1);
        verify(labelRepository, times(1)).deleteById(1);
    }
}