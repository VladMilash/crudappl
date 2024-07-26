package com.mvo.crud.service;

import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;
import com.mvo.crud.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    private PostService postService;

    private Post testPost;

    private Label label;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostServiceImpl(postRepository);
        testPost = new Post(1, "test", null, null, null, PostStatus.ACTIVE, null);

        label = new Label(1, "Test");
    }

    @Test
    public void createPost() {
        when(postRepository.save(any(Post.class))).thenReturn(testPost);
        Post createdPost = postService.createPost(PostStatus.ACTIVE, "Test");
        assertEquals(testPost, createdPost);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void getPostById() {
        when(postRepository.findById(any(Integer.class))).thenReturn(testPost);
        Post findingPost = postService.getPostById(1);
        assertEquals(testPost, findingPost);
        verify(postRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    public void getAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(testPost);
        when(postRepository.findAll()).thenReturn(posts);
        List<Post> findingPosts = postService.getAllPosts();
        assertEquals(1, findingPosts.size());
        assertEquals(posts, findingPosts);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void updatePost() {
        when(postRepository.findById(1)).thenReturn(testPost);
        when(postRepository.update(any(Post.class))).thenReturn(testPost);
        Post createdPost = postService.updatePost(1, PostStatus.ACTIVE, "Test");
        assertEquals(testPost, createdPost);
        verify(postRepository, times(1)).update(any(Post.class));
        verify(postRepository, times(1)).findById(1);
    }

    @Test
    public void deletePostById() {
        postService.deletePostById(1);
        verify(postRepository, times(1)).deleteById(1);
    }

    @Test
    public void findAllLabelsByPostId() {
        List<Label> labels = new ArrayList<>();
        labels.add(label);
        when(postRepository.findAllLabelsByPostId(1)).thenReturn(labels);

        List<Label> findingLabels = postService.findAllLabelsByPostId(1);

        assertEquals(1, findingLabels.size());
        assertEquals(labels, findingLabels);
        verify(postRepository, times(1)).findAllLabelsByPostId(1);
    }

    @Test
    public void deleteAllLabelsByPostId() {
        postService.deleteAllLabelsByPostId(1);
        verify(postRepository, times(1)).deleteAllLabelsByPostId(1);
    }

    @Test
    public void addLabelToPost() {
        postService.addLabelToPost(1, 1);
        verify(postRepository, times(1)).addLabelToPost(1, 1);
    }
}