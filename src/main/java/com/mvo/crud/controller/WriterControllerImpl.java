package com.mvo.crud.controller;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Writer;
import com.mvo.crud.service.WriterService;

import java.util.List;
import java.util.Scanner;

public class WriterControllerImpl implements WriterController {
    WriterService writerService;
    Scanner scanner;

    public WriterControllerImpl(WriterService writerService) {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createWriter() {
        try {
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            Writer writer = writerService.createWriter(firstName, lastName);
            System.out.println("Writer has been created: " + writer);
        } catch (CrudException e) {
            System.out.println("Creating error: " + e.getMessage());
        }
    }

    @Override
    public void getWriterById() {
        try {
            System.out.println("Enter writer ID:");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            Writer writer = writerService.getWriterById(id);
            System.out.println("Writer: " + writer);
        } catch (NotExistCrudException e) {
            System.out.println("Writer with ID not found");
        } catch (CrudException e) {
            System.out.println("Getting writer error: " + e.getMessage());
        }

    }

    @Override
    public void getAllWriters() {
        try {
            List<Writer> writers = writerService.getAllWriters();
            for (Writer writer : writers) {
                System.out.println(writer);
            }
        } catch (CrudException e) {
            System.out.println("Getting writers error: " + e.getMessage());
        }
    }

    @Override
    public void updateWriter() {
        try {
            System.out.println("Enter writer ID:");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            writerService.updateWriter(id, firstName, lastName);
        } catch (NotExistCrudException e) {
            System.out.println("Writer with ID not found");
        } catch (CrudException e) {
            System.out.println("Updating error: " + e.getMessage());
        }
    }

    @Override
    public void deleteWriterById() {
        try {
            System.out.println("Enter writer ID:");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            writerService.deleteWriterById(id);
            System.out.println("Writer with id:  " + id + "has been deleted");
        } catch (NotExistCrudException e) {
            System.out.println("Writer with ID not found");
        } catch (CrudException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
