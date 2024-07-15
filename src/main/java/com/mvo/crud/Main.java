package com.mvo.crud;

import com.mvo.crud.controller.*;
import com.mvo.crud.repository.*;
import com.mvo.crud.service.*;
import com.mvo.crud.view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WriterRepository writerRepository = new JdbcWriterRepositoryImpl();
        WriterService writerService = new WriterServiceImpl(writerRepository);
        WriterController writerController = new WriterControllerImpl(writerService);
        WriterViewImpl writerView = new WriterViewImpl(scanner, writerController);

        PostRepository postRepository = new JdbcPostRepositoryImpl();
        PostService postService = new PostServiceImpl(postRepository);
        PostController postController = new PostControllerImp(postService);
        PostViewImpl postView = new PostViewImpl(scanner, postController);

        LabelRepository labelRepository = new JdbcLabelRepositoryImpl();
        LabelService labelService = new LabelServiceImpl(labelRepository);
        LabelController labelController = new LabelControllerImp(labelService);
        LabelViewImpl labelView = new LabelViewImpl(scanner, labelController);

        WorkingProcess workingProcess = new WorkingProcess(writerView,postView,labelView,scanner);

        workingProcess.runWorkingProcess();

    }
}

