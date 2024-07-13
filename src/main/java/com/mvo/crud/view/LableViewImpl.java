package com.mvo.crud.view;

import com.mvo.crud.controller.LableController;

import java.util.Scanner;

public class LableViewImpl implements LableView {
    private final LableController lableController;
    private final Scanner scanner;
    private String choice = "yes";

    public LableViewImpl(LableController lableController) {
        this.lableController = lableController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void runMenu() {
        try {
            do {
                System.out.println("-----Hello!-----");
                System.out.println("-----Lables Menu-----");
                System.out.println("1. Create Lable");
                System.out.println("2. Get Lable by ID");
                System.out.println("3. Get all Lables");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                int choiceNumber = scanner.nextInt();
                scanner.nextLine();

                switch (choiceNumber) {
                    case 1 -> lableController.createLable();

                    case 2 -> lableController.getLableById();

                    case 3 -> lableController.getAllLables();

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
            lableController.closeScanner();
            scanner.close();
        }
    }
}
