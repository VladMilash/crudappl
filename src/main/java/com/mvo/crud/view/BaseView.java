package com.mvo.crud.view;

import java.util.Scanner;

public abstract class BaseView {
    protected final Scanner scanner;
    private String choice = "yes";

    public BaseView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void runMenu() {
        do {
            displayMenu();
            int choiceNumber = scanner.nextInt();
            handleChoice(choiceNumber);

            if (!choice.equals("no")) {
                System.out.println("Do you want to continue? (Yes/No)");
                choice = scanner.nextLine().trim().toLowerCase();
                while (!choice.equals("yes") && !choice.equals("no")) {
                    System.out.println("Invalid choice. Please try again. (Yes/No)");
                    choice = scanner.nextLine().trim().toLowerCase();
                }
            }

        } while (!choice.equals("no"));
        System.out.println("Return to the main menu");
    }

    protected abstract void displayMenu();

    protected abstract void handleChoice(int choiceNumber);

    public abstract void closeControllerScanner();
}
