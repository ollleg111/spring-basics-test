package com.lesson3.hw3.file_strorage.repo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class GeneralDAO<T> {

    private Class<T> typeParameterClass;
    private static SessionFactory sessionFactory;

    void setTypeClass(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    T save(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method save(T t) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity " + t.getClass().getName() + " was saving");
        return t;
    }

    T update(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method update(T t) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity  " + t.getClass().getName() + " updated");
        return t;
    }

    void delete(long id, String deleteString) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            NativeQuery nativeQuery = session.createNativeQuery(deleteString);
            nativeQuery.addEntity(typeParameterClass);
            nativeQuery.setParameter("id", id);

            nativeQuery.executeUpdate();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("the method delete(long id) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity with id:" + id + " was deleted");
    }

    T findById(long id) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {
            return session.get(typeParameterClass, id);
        } catch (HibernateException e) {
            throw new HibernateException("operation with id: " + id
                    + " was filed in method findById(long id)");
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
