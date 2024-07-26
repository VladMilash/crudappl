package com.mvo.crud.repository.hibernate;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;
import com.mvo.crud.repository.dbutil.HibernateExecutor;
import com.mvo.crud.repository.dbutil.HibernateHelper;
import com.mvo.crud.repository.dbutil.HibernateSessionFactoryUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {

    private final HibernateHelper hibernateHelper = new HibernateHelper();

    @Override
    public List<Post> findAllPostsByWriterId(Integer writerId) {
        return hibernateHelper.executeWithOutTransaction(session -> {
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
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, writerId);
            Post post = session.get(Post.class, postId);
            if (writer == null || post == null) {
                throw new NotExistCrudException(writerId, postId);
            }
            Hibernate.initialize(post.getLabels());
            Hibernate.initialize(writer.getPosts());
            writer.getPosts().add(post);
            session.merge(writer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Adding error");
        }
    }

    @Override
    public Writer findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Writer writer = session.get(Writer.class, id);
            if (writer != null) {
                Hibernate.initialize(writer.getPosts());
            }
            return writer;
        } catch (Exception e) {
            throw new CrudException("Finding error");
        }
    }

    @Override
    public List<Writer> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<Writer> writers = session.createQuery("FROM Writer", Writer.class).list();
            for (Writer writer : writers) {
                Hibernate.initialize(writer.getPosts());
            }
            return writers;
        } catch (Exception e) {
            throw new CrudException("Finding error");
        }
    }

    @Override
    public Writer save(Writer writer) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(writer);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Saving error");
        }
    }

    @Override
    public Writer update(Writer writer) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(writer);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Update error");
        }
    }

    @Override
    public void deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            Hibernate.initialize(writer.getPosts());
            if (writer != null) {
                session.remove(writer);
            } else {
                throw new NotExistCrudException(id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Delete error");
        }
    }
}
