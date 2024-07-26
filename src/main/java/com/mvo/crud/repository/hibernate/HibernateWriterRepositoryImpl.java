package com.mvo.crud.repository.hibernate;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;
import com.mvo.crud.repository.dbutil.HibernateHelper;
import org.hibernate.Hibernate;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {

    private final HibernateHelper hibernateHelper = new HibernateHelper();

    @Override
    public List<Post> findAllPostsByWriterId(Integer writerId) {
        return hibernateHelper.executeWithoutTransaction(session -> {
            Writer writer = session.get(Writer.class, writerId);
            if (writer == null) {
                throw new NotExistCrudException(writerId);
            }
            List<Post> posts = writer.getPosts();
            Hibernate.initialize(posts);
            return posts;
        });
    }

    @Override
    public void deleteAllPostsByWriterId(Integer writerId) {
        hibernateHelper.executeWithTransaction(session -> {
            Writer writer = session.get(Writer.class, writerId);
            if (writer != null) {
                List<Post> posts = writer.getPosts();
                Hibernate.initialize(posts);
                for (Post post : posts) {
                    session.remove(post);
                }
            } else {
                throw new NotExistCrudException(writerId);
            }
            return null;
        });
    }

    @Override
    public void addPostToWriter(Integer writerId, Integer postId) {
        hibernateHelper.executeWithTransaction(session -> {
            Writer writer = session.get(Writer.class, writerId);
            Post post = session.get(Post.class, postId);
            if (writer == null || post == null) {
                throw new NotExistCrudException(writerId, postId);
            }
            Hibernate.initialize(post.getLabels());
            Hibernate.initialize(writer.getPosts());
            writer.getPosts().add(post);
            session.merge(writer);
            return null;
        });
    }

    @Override
    public Writer findById(Integer id) {
        return hibernateHelper.executeWithoutTransaction(session -> {
            Writer writer = session.get(Writer.class, id);
            if (writer != null) {
                Hibernate.initialize(writer.getPosts());
            } else {
                throw new NotExistCrudException(id);
            }
            return writer;
        });
    }

    @Override
    public List<Writer> findAll() {
        return hibernateHelper.executeWithoutTransaction(session -> {
            List<Writer> writers = session.createQuery("FROM Writer", Writer.class).list();
            for (Writer writer : writers) {
                Hibernate.initialize(writer.getPosts());
            }
            return writers;
        });
    }

    @Override
    public Writer save(Writer writer) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.persist(writer);
            return writer;
        });
    }

    @Override
    public Writer update(Writer writer) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.merge(writer);
            return writer;
        });
    }

    @Override
    public void deleteById(Integer id) {
        hibernateHelper.executeWithTransaction(session -> {
            Writer writer = session.get(Writer.class, id);
            if (writer == null) {
                throw new NotExistCrudException(id);
            }
            Hibernate.initialize(writer.getPosts());
            session.remove(writer);
            return null;
        });
    }
}
