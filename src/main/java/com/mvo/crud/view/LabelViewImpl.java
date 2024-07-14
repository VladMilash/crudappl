package com.mvo.crud.view;

import com.mvo.crud.controller.LabelController;

import java.util.Scanner;

public class LabelViewImpl implements LabelView {
    private final LabelController labelController;
    private final Scanner scanner;
    private String choice = "yes";

    public LabelViewImpl(LabelController labelController) {
        this.labelController = labelController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void runMenu() {
        try {
            do {
                System.out.println("-----Hello!-----");
                System.out.println("-----Label Menu-----");
                System.out.println("1. Create Label");
                System.out.println("2. Get Label by ID");
                System.out.println("3. Get all Label");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                int choiceNumber = scanner.nextInt();
                scanner.nextLine();

                switch (choiceNumber) {
                    case 1 -> labelController.createLable();

                    case 2 -> labelController.getLableById();

                    case 3 -> labelController.getAllLables();

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
            labelController.closeScanner();
            scanner.close();
        }
    }
}
