package com.mvo.crud.controller;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;
import com.mvo.crud.service.PostService;

import java.util.List;
import java.util.Scanner;

public class PostControllerImp implements PostController {

    private final PostService postService;
    private final Scanner scanner;

    public PostControllerImp(PostService postService) {
        this.postService = postService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createPost() {
        try {
            System.out.println("Enter content:");
            String content = scanner.nextLine();
            String postStatus = readPostStatus();
            Post post = postService.createPost(PostStatus.valueOf(postStatus), content);
            System.out.println("Post has been created: " + post);
        } catch (CrudException e) {
            System.out.println("Creating error: " + e.getMessage());
        }
    }

    @Override
    public void getPostById() {
        try {
            System.out.println("Enter post ID:");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            Post post = postService.getPostById(id);
            System.out.println("Post: " + post);
        } catch (NotExistCrudException e) {
            System.out.println("Post with ID not found");
        } catch (CrudException e) {
            System.out.println("Getting post error: " + e.getMessage());
        }
    }

    @Override
    public void getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            for (Post post : posts) {
                System.out.println(post);
            }
        } catch (CrudException e) {
            System.out.println("Getting post error: " + e.getMessage());
        }
    }

    @Override
    public void updatePost() {
        Integer id = null;
        try {
            System.out.println("Enter post ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            String postStatus = readPostStatus();
            System.out.println("Enter content:");
            String content = scanner.nextLine();
            postService.updatePost(id, PostStatus.valueOf(postStatus), content);
            System.out.println("Post has been updated.");
        } catch (NotExistCrudException e) {
            System.out.println("Post with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Updating error: " + e.getMessage());
        }
    }

    @Override
    public void deletePostById() {
        Integer id = null;
        try {
            System.out.println("Enter post ID:");
            id = scanner.nextInt();
            scanner.nextLine();
            postService.deletePostById(id);
            System.out.println("Post with id :  " + id + "has been deleted");
        } catch (NotExistCrudException e) {
            System.out.println("Post with " + id + " not found");
        } catch (CrudException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }

    }

    @Override
    public void closeScanner() {
        scanner.close();
    }

    private String readPostStatus() {
        String postStatus;
        while (true) {
            System.out.println("Choose status: ACTIVE, UNDER_REVIEW, DELETED");
            postStatus = scanner.nextLine();

            if (postStatus.equals("ACTIVE") || postStatus.equals("UNDER_REVIEW") || postStatus.equals("DELETED")) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again: ACTIVE, UNDER_REVIEW, DELETED");
            }
        }
        return postStatus;
    }
}
