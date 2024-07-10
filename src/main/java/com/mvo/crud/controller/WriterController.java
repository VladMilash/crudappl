package com.mvo.crud.controller;

import com.mvo.crud.model.Writer;
import com.mvo.crud.service.WriterService;

import java.util.List;
import java.util.Scanner;

public class WriterController {
    WriterService writerService;
    Scanner scanner;

    public WriterController(WriterService writerService, Scanner scanner) {
        this.writerService = writerService;
        this.scanner = scanner;
    }

    public void createWriter() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        Writer writer = writerService.createWriter(firstName, lastName);
        System.out.println("Writer has been created: " + writer);
    }

    public void getWriterById() {
        System.out.println("Enter writer ID:");
        Integer id = scanner.nextInt();
        Writer writer = writerService.getWriterById(id);
        System.out.println("Writer: " + writer );
    }

    public void getAllWriters() {
        List<Writer> writers = writerService.getAllWriters();
        for (Writer writer : writers) {
            System.out.println(writer);
        }
    }

    public void updateWriter() {
        System.out.println("Enter writer ID:");
        Integer id = scanner.nextInt();
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        writerService.updateWriter(id,firstName,lastName);
    }

    public void deleteWriterById() {
        System.out.println("Enter writer ID:");
        Integer id = scanner.nextInt();
        writerService.deleteWriterById(id);
        System.out.println("Writer with id:  " + id +  "has been deleted");
    }
}
