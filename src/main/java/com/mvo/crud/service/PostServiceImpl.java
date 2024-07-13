package com.mvo.crud.service;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;
import com.mvo.crud.repository.PostRepository;

import java.time.LocalDate;
import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostStatus postStatus, String content) {
        Post post = new Post(content, LocalDate.now(), LocalDate.now(), null, postStatus);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post getPostById(Integer id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new NotExistCrudException(id);
        }
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Integer id, PostStatus postStatus, String content) {
        Post updatedPost = postRepository.findById(id);
        updatedPost.setPostStatus(postStatus);
        updatedPost.setContent(content);
        updatedPost.setUpdated(LocalDate.now());
        postRepository.update(updatedPost);
        return updatedPost;
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }
}
