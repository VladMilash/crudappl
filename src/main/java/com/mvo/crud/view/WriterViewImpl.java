package com.mvo.crud.view;

import com.mvo.crud.controller.WriterController;

import java.util.Scanner;

public class WriterViewImpl extends BaseView {
    private final WriterController writerController;

    public WriterViewImpl(Scanner scanner, WriterController writerController) {
        super(scanner);
        this.writerController = writerController;
    }


    @Override
    public void closeControllerScanner() {
        writerController.closeScanner();
    }

    @Override
    protected void displayMenu() {
        System.out.println("-----Hello!-----");
        System.out.println("-----Writers Menu-----");
        System.out.println("1. Create Writer");
        System.out.println("2. Get Writer by ID");
        System.out.println("3. Update Writer");
        System.out.println("4. Delete Writer");
        System.out.println("5. Get all Writers");
        System.out.println("6. Find all posts by Writer");
        System.out.println("7. Delete all posts by Writer");
        System.out.println("8. Add post to Writer");
        System.out.println("9. Exit");
        System.out.print("Select an option: ");
    }

    @Override
    protected void handleChoice(int choiceNumber) {
        switch (choiceNumber) {
            case 1 -> writerController.createWriter();

            case 2 -> writerController.getWriterById();

            case 3 -> writerController.updateWriter();

            case 4 -> writerController.deleteWriterById();

            case 5 -> writerController.getAllWriters();

            case 6 -> writerController.findAllPostsByWriterId();

            case 7 -> writerController.deleteAllPostsByWriterId();

            case 8 -> writerController.addPostToWriter();

            case 9 -> {
                System.out.println("Exiting...");
                setChoice("no");
            }
            default -> System.out.println("Invalid choice");
        }

    }
}
