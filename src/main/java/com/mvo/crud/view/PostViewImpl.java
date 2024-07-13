package com.mvo.crud.view;

import com.mvo.crud.controller.PostController;
import com.mvo.crud.controller.PostControllerImp;

import java.util.Scanner;

public class PostViewImpl implements PostView {

    private final PostController postControllerImp;
    private final Scanner scanner;
    private String choice = "yes";

    public PostViewImpl(PostController postControllerImp) {
        this.postControllerImp = postControllerImp;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void runMenu() {
        try {
            do {
                System.out.println("-----Hello!-----");
                System.out.println("-----PostsMenu-----");
                System.out.println("1. Create Post");
                System.out.println("2. Get Post by ID");
                System.out.println("3. Get all Posts");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                int choiceNumber = scanner.nextInt();
                scanner.nextLine();

                switch (choiceNumber) {
                    case 1 -> postControllerImp.createPost();

                    case 2 -> postControllerImp.getPostById();

                    case 3 -> postControllerImp.getAllPost();

                    case 4 -> {
                        System.out.println("Exiting...");
                        choice = "no";
                    }
                    default -> System.out.println("Invalid choice");
                }

                if (!choice.equals("no")) {
                    System.out.println("Do you want to continue? (Yes/No)");
                    choice = scanner.nextLine().trim().toLowerCase();
                    while (!choice.equals("yes") && !choice.equals("no")) {
                        System.out.println("Invalid choice. Please try again. (Yes/No)");
                        choice = scanner.nextLine().trim().toLowerCase();
                    }
                }

            } while (!choice.equals("no"));
            System.out.println("The program is completed, have a nice day");

        } finally {
            postControllerImp.closeScanner();
            scanner.close();
        }
    }

}
