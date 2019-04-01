package Gym.Model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class SessionFactoryDao {
    static {
        try {
            sessionFactory =  new AnnotationConfiguration().
            		configure().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static final SessionFactory sessionFactory;


    public static SessionFactory getSession() {
        return sessionFactory;
    }
}