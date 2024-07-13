package com.mvo.crud;

import com.mvo.crud.controller.PostController;
import com.mvo.crud.controller.PostControllerImp;
import com.mvo.crud.controller.WriterController;
import com.mvo.crud.controller.WriterControllerImpl;
import com.mvo.crud.repository.JdbcPostRepositoryImpl;
import com.mvo.crud.repository.JdbcWriterRepositoryImpl;
import com.mvo.crud.service.PostService;
import com.mvo.crud.service.PostServiceImpl;
import com.mvo.crud.service.WriterService;
import com.mvo.crud.service.WriterServiceImpl;
import com.mvo.crud.view.PostView;
import com.mvo.crud.view.PostViewImpl;
import com.mvo.crud.view.WriterView;
import com.mvo.crud.view.WriterViewImpl;

public class Main {
    public static void main(String[] args) {
//        JdbcWriterRepositoryImpl jdbcWriterRepository = new JdbcWriterRepositoryImpl();
//        WriterService writerService = new WriterServiceImpl(jdbcWriterRepository);
//        WriterController writerController = new WriterControllerImpl(writerService);
//        WriterView writerView = new WriterViewImpl(writerController);
//
//        writerView.runMenu();

        JdbcPostRepositoryImpl jdbcPostRepository = new JdbcPostRepositoryImpl();
        PostService postService = new PostServiceImpl(jdbcPostRepository);
        PostController postController = new PostControllerImp(postService);
        PostView postView = new PostViewImpl(postController);

        postView.runMenu();

    }
}
