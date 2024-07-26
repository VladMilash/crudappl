package com.mvo.crud.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalDate created;
    private LocalDate updated;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_label",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    public Post() {
    }

    public Post(int id, String content, LocalDate created, LocalDate updated, List<Label> labels, PostStatus postStatus, Writer writer) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.postStatus = postStatus;
        this.writer = writer;
    }

    public Post(String content, LocalDate created, LocalDate updated, List<Label> labels, PostStatus postStatus) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.postStatus = postStatus;
    }

    public Post(int id, String content, LocalDate created, LocalDate updated, List<Label> labels, PostStatus postStatus) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.postStatus = postStatus;
    }

    public Post(PostStatus postStatus, String content) {
        this.postStatus = postStatus;
        this.content = content;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                ", postStatus=" + postStatus +
                ", writer=" + writer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(content, post.content) && Objects.equals(created, post.created) && Objects.equals(updated, post.updated) && Objects.equals(labels, post.labels) && postStatus == post.postStatus && writer == post.writer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created, updated, labels, postStatus, writer);
    }
}
