package com.mvo.crud;

import com.mvo.crud.controller.*;
import com.mvo.crud.repository.*;
import com.mvo.crud.service.*;
import com.mvo.crud.view.*;

public class Main {
    public static void main(String[] args) {
//        JdbcWriterRepositoryImpl jdbcWriterRepository = new JdbcWriterRepositoryImpl();
//        WriterService writerService = new WriterServiceImpl(jdbcWriterRepository);
//        WriterController writerController = new WriterControllerImpl(writerService);
//        WriterView writerView = new WriterViewImpl(writerController);
//
//        writerView.runMenu();
//
//        PostRepository postRepository = new JdbcPostRepositoryImpl();
//        PostService postService = new PostServiceImpl(postRepository);
//        PostController postController = new PostControllerImp(postService);
//        PostView postView = new PostViewImpl(postController);
//
//        postView.runMenu();

        LableRepository lableRepository = new JdbcLableRepositoryImpl();
        LableService lableService = new LableServiceImpl(lableRepository);
        LableController lableController = new LableControllerImp(lableService);
        LableView lableView = new LableViewImpl(lableController);

        lableView.runMenu();

    }
}
