package com.lesson3.hw3.file_storage.repo;

import com.lesson3.hw3.file_storage.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class GeneralDAO<T> {

    private Class<T> typeParameterClass;
    private HibernateUtil hibernateUtil;

    void setTypeClass(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    T save(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("The method save(T t) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity " + t.getClass().getName() + " was saving");
        return t;
    }

    T update(T t) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("The method update(T t) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity  " + t.getClass().getName() + " updated");
        return t;
    }

    void delete(long id, String deleteString) throws HibernateException {
        Transaction tr = null;
        try (Session session = hibernateUtil.openSession()) {
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(deleteString, typeParameterClass);
            query.setParameter("id", id);

            query.executeUpdate();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new HibernateException("The method delete(long id) was failed in class "
                    + typeParameterClass.getName());
        }
        System.out.println("Entity with id:" + id + " was deleted");
    }

    T findById(long id) throws HibernateException {
        try (Session session = hibernateUtil.openSession()) {
            return session.get(typeParameterClass, id);
        } catch (HibernateException e) {
            throw new HibernateException("Operation with id: " + id
                    + " was filed in method findById(long id) from class " +
                    typeParameterClass.getName());
        }
    }
}
