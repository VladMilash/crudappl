package com.mvo.crud.controller;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Lable;
import com.mvo.crud.service.LableService;

import java.util.List;
import java.util.Scanner;

public class LableControllerImp implements LableController {

    private final LableService lableService;

    private final Scanner scanner;

    public LableControllerImp(LableService lableService) {
        this.lableService = lableService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createLable() {
        try {
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            Lable lable = lableService.createLable(name);
            System.out.println("Lable has been created: " + lable);
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
            Lable lable = lableService.getLableById(id);
            System.out.println("Lable: " + lable);
        } catch (NotExistCrudException e) {
            System.out.println("lable with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Getting lable error: " + e.getMessage());
        }
    }

    @Override
    public void getAllLables() {
        try {
            List<Lable> lables = lableService.getAllLables();
            for (Lable lable : lables) {
                System.out.println(lable);
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
