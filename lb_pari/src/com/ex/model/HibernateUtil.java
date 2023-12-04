package com.ex.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Handle the exception appropriately or log it
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Manufacturer> watches = session.createQuery("FROM Teacher ", Teacher.class).list();
        watches.forEach(System.out::println);
        transaction.commit();
        session.close();

    }
}