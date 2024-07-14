package com.mvo.crud.controller;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Label;
import com.mvo.crud.service.LableService;

import java.util.List;
import java.util.Scanner;

public class LabelControllerImp implements LabelController {

    private final LableService lableService;

    private final Scanner scanner;

    public LabelControllerImp(LableService lableService) {
        this.lableService = lableService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createLable() {
        try {
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            Label label = lableService.createLable(name);
            System.out.println("Lable has been created: " + label);
        } catch (CrudException e) {
            System.out.println("Creating error: " + e.getMessage());
        }
    }

    @Override
    public void getLableById() {
        Integer id = null;
        try {
            System.out.println("Enter lable ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            Label label = lableService.getLableById(id);
            System.out.println("Lable: " + label);
        } catch (NotExistCrudException e) {
            System.out.println("lable with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Getting lable error: " + e.getMessage());
        }
    }

    @Override
    public void getAllLables() {
        try {
            List<Label> labels = lableService.getAllLables();
            for (Label label : labels) {
                System.out.println(label);
            }
        } catch (CrudException e) {
            System.out.println("Getting lables error: " + e.getMessage());
        }
    }

    @Override
    public void updateLable() {
        Integer id = null;
        try {
            System.out.println("Enter post ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            lableService.updateLable(id, name);
            System.out.println("Lable has been updated.");
        } catch (NotExistCrudException e) {
            System.out.println("Lable with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Updating error: " + e.getMessage());
        }
    }

    @Override
    public void deleteLableById() {
        Integer id = null;
        try {
            System.out.println("Enter lable ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            lableService.deleteLableById(id);
            System.out.println("Lable with id:  " + id + "has been deleted");
        } catch (NotExistCrudException e) {
            System.out.println("Lable with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void closeScanner() {
        scanner.close();
    }
}
