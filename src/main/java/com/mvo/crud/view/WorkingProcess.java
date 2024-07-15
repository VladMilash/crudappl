package com.mvo.crud.view;

import java.util.Scanner;

public class WorkingProcess {
    private final BaseView writerView;
    private final BaseView postView;
    private final BaseView labelViewImpl;
    private final Scanner scanner;
    private String choice = "yes";

    public WorkingProcess(BaseView writerView, BaseView postView, BaseView labelViewImpl, Scanner scanner) {
        this.writerView = writerView;
        this.postView = postView;
        this.labelViewImpl = labelViewImpl;
        this.scanner = scanner;
    }

    public void runWorkingProcess() {
        try {
            do {
                System.out.println("-----Hello!-----");
                System.out.println("Which menu should I call?");
                System.out.println("1. Writer Menu");
                System.out.println("2. Post Menu");
                System.out.println("3. Label Menu");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                int choiceNumber = scanner.nextInt();
                scanner.nextLine();
                switch (choiceNumber) {
                    case 1 -> writerView.runMenu();

                    case 2 -> postView.runMenu();

                    case 3 -> labelViewImpl.runMenu();

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
            scanner.close();
            writerView.closeControllerScanner();
            postView.closeControllerScanner();
            labelViewImpl.closeControllerScanner();
        }
    }
}
