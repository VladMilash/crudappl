package com.mvo.crud.view;

import com.mvo.crud.controller.WriterController;

import java.util.Scanner;

public class WriterViewImpl implements WriterView {
    private final WriterController writerController;
    private final Scanner scanner;
    String choice = "yes";


    public WriterViewImpl(WriterController writerController) {
        this.writerController = writerController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void runMenu() {
        do {
            System.out.println("-----Hello!-----");
            System.out.println("-----WritersMenu-----");
            System.out.println("1. Create Writer");
            System.out.println("2. Get Writer by ID");
            System.out.println("3. Get All Writers");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choiceNumber = scanner.nextInt();
            scanner.nextLine();

            switch (choiceNumber) {
                case 1 -> writerController.createWriter();

                case 2 -> writerController.getWriterById();

                case 3 -> writerController.getAllWriters();

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
        writerController.closeScanner();
        scanner.close();
    }
}
