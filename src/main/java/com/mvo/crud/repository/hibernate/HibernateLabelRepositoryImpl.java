package com.mvo.crud.repository.hibernate;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;
import com.mvo.crud.repository.LabelRepository;
import com.mvo.crud.repository.dbutil.HibernateHelper;
import org.hibernate.Hibernate;

import java.util.List;

public class HibernateLabelRepositoryImpl implements LabelRepository {
    HibernateHelper hibernateHelper = new HibernateHelper();

    @Override
    public Label findById(Integer id) {
        Label label = hibernateHelper.executeWithoutTransaction(session -> session.get(Label.class, id));
        if (label == null) {
            throw new NotExistCrudException(id);
        }
        return label;
    }

    @Override
    public List<Label> findAll() {
        return hibernateHelper.executeWithoutTransaction(session -> session.createQuery("FROM Label", Label.class).list());
    }

    @Override
    public Label save(Label label) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.persist(label);
            return label;
        });
    }

    @Override
    public Label update(Label label) {
        return hibernateHelper.executeWithTransaction(session -> {
            session.merge(label);
            return label;
        });
    }

    @Override
    public void deleteById(Integer id) {
        hibernateHelper.executeWithTransaction(session -> {
            Label label = session.get(Label.class, id);
            if (label == null) {
                throw new NotExistCrudException(id);
            }
            session.remove(label);
            return null;
        });
    }
}
