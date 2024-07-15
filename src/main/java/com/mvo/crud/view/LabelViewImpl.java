package com.mvo.crud.view;

import com.mvo.crud.controller.LabelController;

import java.util.Scanner;

public class LabelViewImpl extends BaseView {
    private final LabelController labelController;


    public LabelViewImpl(Scanner scanner, LabelController labelController) {
        super(scanner);
        this.labelController = labelController;
    }

    @Override
    public void closeControllerScanner() {
        labelController.closeScanner();
    }

    @Override
    protected void displayMenu() {
        System.out.println("-----Hello!-----");
        System.out.println("-----Labels Menu-----");
        System.out.println("1. Create Label");
        System.out.println("2. Get Label by ID");
        System.out.println("3. Get all Labels");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    @Override
    protected void handleChoice(int choiceNumber) {
        switch (choiceNumber) {
            case 1 -> labelController.createLable();

            case 2 -> labelController.getLableById();

            case 3 -> labelController.getAllLables();

            case 4 -> {
                System.out.println("Exiting...");
                setChoice("no");
            }
            default -> System.out.println("Invalid choice");
        }
    }
}
