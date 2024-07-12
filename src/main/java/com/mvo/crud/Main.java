package com.mvo.crud;

import com.mvo.crud.controller.WriterController;
import com.mvo.crud.controller.WriterControllerImpl;
import com.mvo.crud.repository.JdbcWriterRepositoryImpl;
import com.mvo.crud.service.WriterService;
import com.mvo.crud.service.WriterServiceImpl;
import com.mvo.crud.view.WriterView;
import com.mvo.crud.view.WriterViewImpl;

public class Main {
    public static void main(String[] args) {
        JdbcWriterRepositoryImpl jdbcWriterRepository = new JdbcWriterRepositoryImpl();
        WriterService writerService = new WriterServiceImpl(jdbcWriterRepository);
        WriterController writerController = new WriterControllerImpl(writerService);
        WriterView writerView = new WriterViewImpl(writerController);

        writerView.runMenu();
    }
}
