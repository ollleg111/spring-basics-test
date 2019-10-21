package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

abstract class GeneralDAO<T> {

    private Class typeClass;
    private HibernateUtil hibernateUtil;

    void setTypeClass(Class typeClass) {
        this.typeClass = typeClass;
    }
    void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    T save(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.startSession()) {
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
                    + t.getClass().getName());
        }
        System.out.println("Entity " + t.getClass().getName() + " was saving");
        return t;
    }

    T update(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.startSession()) {
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
                    + t.getClass().getName());
        }
        System.out.println("Entity  " + t.getClass().getName() + " updated");
        return t;
    }

    void delete(long id, String deleteString) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.startSession()) {
            tr = session.getTransaction();
            tr.begin();

            NativeQuery nativeQuery = session.createNativeQuery(deleteString);
            nativeQuery.addEntity(typeClass);
            nativeQuery.setParameter("id", id);

            nativeQuery.executeUpdate();

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

    T findById(long id) throws HibernateException {
        try (Session session = hibernateUtil.startSession()) {
            return (T) session.get(typeClass, id);
        } catch (HibernateException e) {
            throw new HibernateException("operation with id: " + id
                    + " was filed in method findById(long id)");
        }
    }
}
