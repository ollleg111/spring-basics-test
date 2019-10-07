package com.hw2.repo;

import com.hw2.model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {

    private static SessionFactory sessionFactory;

    public Item save(Item item) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method save(Item item) was failed");
        }
        System.out.println("Entity " + item.getClass().getName() + " was saving");
        return item;
    }

    public Item update(Item item) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method update(Item item) was failed");
        }
        System.out.println("Entity  " + item.getClass().getName() + " updated");
        return item;
    }

    public void delete(long id) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Item.class, id));

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method delete(long id) was failed");
        }
        System.out.println("Entity with id:" + id + " was deleted");
    }

    public Item findById(long id) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {
            return session.get(Item.class, id);
        } catch (HibernateException e) {
            throw new HibernateException("operation with id: " + id + " was filed in method findById(long id)");
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
