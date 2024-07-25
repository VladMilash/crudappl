package com.mvo.crud.repository.dbutil;

import com.mvo.crud.model.Label;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.io.InputStream;
import java.util.Properties;


public class HibernateSessionFactoryUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateSessionFactoryUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            try (InputStream inputStream = HibernateSessionFactoryUtil.class
                    .getClassLoader().getResourceAsStream("hibernate.properties")) {
                if (inputStream != null) {
                    properties.load(inputStream);
                } else {
                    throw new RuntimeException("hibernate.properties file not found");
                }
            }

            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Writer.class);
            configuration.addAnnotatedClass(Post.class);
            configuration.addAnnotatedClass(Label.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed." + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
