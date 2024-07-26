package com.mvo.crud.repository.hibernate;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;
import com.mvo.crud.repository.PostRepository;
import com.mvo.crud.repository.dbutil.HibernateHelper;
import org.hibernate.Hibernate;

import java.util.List;

public class HibernatePostRepositoryImpl implements PostRepository {
    HibernateHelper hibernateHelper = new HibernateHelper();

    @Override
    public List<Label> findAllLabelsByPostId(Integer postId) {
        return hibernateHelper.executeWithoutTransaction(session -> {
            Post post = session.get(Post.class, postId);
            if (post == null) {
                throw new NotExistCrudException(postId);
            }
            List<Label> labels = post.getLabels();
            Hibernate.initialize(labels);
            return labels;
        });
    }

    @Override
    public void deleteAllLabelsByPostId(Integer postId) {
        hibernateHelper.executeWithTransaction(session -> {
            Post post = session.get(Post.class, postId);
            if (post != null) {
                List<Label> labels = post.getLabels();
                Hibernate.initialize(labels);
                for (Label label : labels) {
                    post.getLabels().remove(label);
                }
            } else {
                throw new NotExistCrudException(postId);
            }
            return null;
        });
    }

    @Override
    public void addLabelToPost(Integer postId, Integer labelId) {
        hibernateHelper.executeWithTransaction(session -> {
            Post post = session.get(Post.class, postId);
            Label label = session.get(Label.class, labelId);
            if (post == null || label == null) {
                throw new NotExistCrudException(postId, labelId);
            }
            Hibernate.initialize(post.getLabels());
            post.getLabels().add(label);
            session.merge(post);
            return null;
        });

    }

    @Override
    public Post findById(Integer id) {
        return hibernateHelper.executeWithoutTransaction(session -> {
            Post post = session.get(Post.class, id);
            if (post != null) {
                Hibernate.initialize(post.getLabels());
            } else {
                throw new NotExistCrudException(id);
            }
            return post;
        });
    }

    @Override
    public List<Post> findAll() {
        return hibernateHelper.executeWithoutTransaction(session -> {
            List<Post> posts = session.createQuery("FROM Post", Post.class).list();
            for (Post post : posts) {
                Hibernate.initialize(post.getLabels());
            }
            return posts;
        });
    }

    @Override
    public Post save(Post post) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.persist(post);
            return post;
        });
    }

    @Override
    public Post update(Post post) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.merge(post);
            return post;
        });
    }

    @Override
    public void deleteById(Integer id) {
        hibernateHelper.executeWithTransaction(session -> {
            Post post = session.get(Post.class, id);
            if (post == null) {
                throw new NotExistCrudException(id);
            }
            Hibernate.initialize(post.getLabels());
            session.remove(post);
            return null;
        });
    }
}
