package com.mvo.crud.view;

import com.mvo.crud.controller.PostController;

import java.util.Scanner;

public class PostViewImpl extends BaseView {

    private final PostController postController;


    public PostViewImpl(Scanner scanner, PostController postController) {
        super(scanner);
        this.postController = postController;
    }

    @Override
    public void closeControllerScanner() {
        postController.closeScanner();
    }

    @Override
    protected void displayMenu() {
        System.out.println("-----Hello!-----");
        System.out.println("-----Posts Menu-----");
        System.out.println("1. Create Post");
        System.out.println("2. Get Post by ID");
        System.out.println("3. Get all Posts");
        System.out.println("4. Update Post");
        System.out.println("5. Delete Post");
        System.out.println("6. Find all Labels by Post");
        System.out.println("7. Delete all Labels by Post");
        System.out.println("8. Add Label to Post");
        System.out.println("9. Exit");
        System.out.print("Select an option: ");

    }

    @Override
    protected void handleChoice(int choiceNumber) {
        switch (choiceNumber) {
            case 1 -> postController.createPost();

            case 2 -> postController.getPostById();

            case 3 -> postController.getAllPosts();

            case 4 -> postController.updatePost();

            case 5 -> postController.deletePostById();

            case 6 -> postController.findAllLabelsByPostId();

            case 7 -> postController.deleteAllLabelsByPostId();

            case 8 -> postController.addLabelToPost();

            case 9 -> {
                System.out.println("Exiting...");
                setChoice("no");
            }
            default -> System.out.println("Invalid choice");
        }
    }
}
