package com.mvo.crud.controller;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.service.WriterService;

import java.util.List;
import java.util.Scanner;

public class WriterControllerImpl implements WriterController {
    private final WriterService writerService;
    private final Scanner scanner;

    public WriterControllerImpl(WriterService writerService) {
        this.writerService = writerService;
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
        Integer id = null;
        try {
            System.out.println("Enter writer ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            Writer writer = writerService.getWriterById(id);
            System.out.println("Writer: " + writer);
        } catch (NotExistCrudException e) {
            System.out.println("Writer with " + id + " not found");
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
        Integer id = null;
        try {
            System.out.println("Enter writer ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            writerService.updateWriter(id, firstName, lastName);
            System.out.println("Writer has been updated.");
        } catch (NotExistCrudException e) {
            System.out.println("Writer with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Updating error: " + e.getMessage());
        }
    }

    @Override
    public void deleteWriterById() {
        Integer id = null;
        try {
            System.out.println("Enter writer ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            writerService.deleteWriterById(id);
            System.out.println("Writer with id:  " + id + " has been deleted");
        } catch (NotExistCrudException e) {
            System.out.println("Writer with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void findAllPostsByWriterId() {
        Integer id = null;
        try {
            System.out.println("Enter writer ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            List<Post> posts = writerService.findAllPostsByWriterId(id);
            for (Post post : posts) {
                System.out.println("Post: " + post);
            }
        } catch (CrudException e) {
            System.out.println("Finding error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllPostsByWriterId() {
        Integer id = null;
        try {
            System.out.println("Enter writer ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            writerService.deleteAllPostsByWriterId(id);
            System.out.println("All posts for writer with id:  " + id + " has been deleted");
        } catch (CrudException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void addPostToWriter() {
        Integer writerId = null;
        Integer postId = null;
        try {
            System.out.println("Enter writer ID:");
            writerId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter post ID:");
            postId = scanner.nextInt();
            writerService.addPostToWriter(writerId, postId);
            System.out.println("Added a post with an ID: " + postId + " to the writer with an ID: " + writerId);
        } catch (NotExistCrudException e) {
            System.out.println("No writer or post found with: " + " writer ID: " + writerId + " post ID " + postId);
        } catch (CrudException e) {
            System.out.println("Adding error: " + e.getMessage());
        }
    }

    @Override
    public void closeScanner() {
        scanner.close();
    }
}
