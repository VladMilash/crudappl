package com.mvo.crud;

import com.mvo.crud.controller.*;
import com.mvo.crud.repository.*;
import com.mvo.crud.repository.hibernate.HibernateLabelRepositoryImpl;
import com.mvo.crud.repository.hibernate.HibernatePostRepositoryImpl;
import com.mvo.crud.repository.hibernate.HibernateWriterRepositoryImpl;
import com.mvo.crud.repository.jdbc.JdbcLabelRepositoryImpl;
import com.mvo.crud.repository.jdbc.JdbcPostRepositoryImpl;
import com.mvo.crud.repository.jdbc.JdbcWriterRepositoryImpl;
import com.mvo.crud.service.*;
import com.mvo.crud.view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WriterRepository writerRepository = new HibernateWriterRepositoryImpl();
        WriterService writerService = new WriterServiceImpl(writerRepository);
        WriterController writerController = new WriterControllerImpl(writerService);
        BaseView writerView = new WriterViewImpl(scanner, writerController);

        PostRepository postRepository = new HibernatePostRepositoryImpl();
        PostService postService = new PostServiceImpl(postRepository);
        PostController postController = new PostControllerImp(postService);
        BaseView postView = new PostViewImpl(scanner, postController);

        LabelRepository labelRepository = new HibernateLabelRepositoryImpl();
        LabelService labelService = new LabelServiceImpl(labelRepository);
        LabelController labelController = new LabelControllerImp(labelService);
        BaseView labelView = new LabelViewImpl(scanner, labelController);

        WorkingProcess workingProcess = new WorkingProcess(writerView,postView,labelView,scanner);

        workingProcess.runWorkingProcess();

    }
}

