package com.mvo.crud.repository.hibernate;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;
import com.mvo.crud.repository.dbutil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {
    @Override
    public List<Post> findAllPostsByWriterId(Integer writerId) {
        Writer writer = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Writer.class, writerId);
        List<Post> posts = writer.getPosts();
        return posts;
    }

    @Override
    public void deleteAllPostsByWriterId(Integer writerId) {


    }

    @Override
    public void addPostToWriter(Integer writerId, Integer postId) {

    }

    @Override
    public Writer findById(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Writer.class, id);
    }

    @Override
    public List<Writer> findAll() {
        List<Writer> writers = (List<Writer>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Writer").list();
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(writer);
        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(writer);
        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Writer writer = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Writer.class, id);
        session.remove(writer);
        transaction.commit();
        session.close();
    }
}
