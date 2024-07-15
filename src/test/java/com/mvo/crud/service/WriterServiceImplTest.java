package com.mvo.crud.service;


import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WriterServiceImplTest {

    @Mock
    private WriterRepository writerRepository;

    private WriterServiceImpl writerService;

    private Writer testWriter;

    private Post post;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        writerService = new WriterServiceImpl(writerRepository);
        testWriter = new Writer("Ivan", "Ivanov");
        testWriter.setId(1);

        post = new Post(PostStatus.ACTIVE, "Test");
        post.setId(1);
    }

    @Test
    public void createWriter() {
        when(writerRepository.save(any(Writer.class))).thenReturn(testWriter);
        Writer createdWriter = writerService.createWriter("Ivan", "Ivanov");
        assertEquals(testWriter, createdWriter);
        verify(writerRepository, times(1)).save(any(Writer.class));
    }

    @Test
    public void getWriterById() {
        when(writerRepository.findById(any(Integer.class))).thenReturn(testWriter);
        Writer findingWriter = writerService.getWriterById(1);
        assertEquals(testWriter, findingWriter);
        verify(writerRepository, times(1)).findById(any(Integer.class));
    }


    @Test
    public void getAllWriters() {
        List<Writer> writers = new ArrayList<>();
        writers.add(testWriter);
        when(writerRepository.findAll()).thenReturn(writers);
        List<Writer> findingWriters = writerService.getAllWriters();
        assertEquals(1, findingWriters.size());
        assertEquals(writers, findingWriters);
        verify(writerRepository, times(1)).findAll();
    }

    @Test
    public void updateWriter() {
        when(writerRepository.findById(1)).thenReturn(testWriter);
        when(writerRepository.update(any(Writer.class))).thenReturn(testWriter);
        Writer createdWriter = writerService.updateWriter(1, "Ivan", "Ivanov");
        assertEquals(testWriter, createdWriter);
        verify(writerRepository, times(1)).update(any(Writer.class));
        verify(writerRepository, times(1)).findById(1);
    }

    @Test
    public void deleteWriterById() {
        writerService.deleteWriterById(1);
        verify(writerRepository, times(1)).deleteById(1);
    }

    @Test
    public void findAllPostsByWriterId() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(writerRepository.findAllPostsByWriterId(1)).thenReturn(posts);

        List<Post> findingPosts = writerService.findAllPostsByWriterId(1);

        assertEquals(1, findingPosts.size());
        assertEquals(posts, findingPosts);
        verify(writerRepository, times(1)).findAllPostsByWriterId(1);
    }

    @Test
    public void deleteAllPostsByWriterId() {
        writerService.deleteAllPostsByWriterId(1);
        verify(writerRepository, times(1)).deleteAllPostsByWriterId(1);
    }

    @Test
    public void addPostToWriter() {
        post.setId(1);
        writerService.addPostToWriter(1, 1);
        verify(writerRepository, times(1)).addPostToWriter(1, 1);
    }
}