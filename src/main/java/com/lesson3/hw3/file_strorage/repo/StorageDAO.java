package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.util.HibernateUtil;
import com.lesson3.hw3.file_strorage.constants.Constants;
import com.lesson3.hw3.file_strorage.exceptions.BadRequestException;
import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class StorageDAO extends GeneralDAO<Storage> {
    private HibernateUtil hibernateUtil;

    @Autowired
    public StorageDAO(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
        setHibernateUtil(hibernateUtil);
        setTypeClass(Storage.class);
    }

    @Override
    public Storage save(Storage storage) throws HibernateException {
        return super.save(storage);
    }

    @Override
    public Storage update(Storage storage) throws HibernateException {
        return super.update(storage);
    }

    public void delete(long id) throws HibernateException {
        super.delete(id, Constants.STORAGE_REQUEST_DELETE);
    }

    @Override
    public Storage findById(long id) throws HibernateException {
        return super.findById(id);
    }

    public long getStorageAmount(Storage storage) throws BadRequestException {
        try (Session session = hibernateUtil.openSession()) {
            Query<Long> query = session.createQuery(Constants.FIND_AMOUNT_OF_STORAGE, Long.class);
            query.setParameter("id", storage.getId());

            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new BadRequestException("Storage id: " + storage.getId() + " was not found in method " +
                    "getStorageSize(Storage storage) from class " + StorageDAO.class.getName());
        }
    }

    public List<File> filesList(long storageId) throws HibernateException {
        try (Session session = hibernateUtil.openSession()) {

            Query<File> query = session.createQuery(Constants.FILES_REQUEST_FIND_BY_STORAGE_ID, File.class);
            query.setParameter("id", storageId);

            return query.list();
        } catch (HibernateException e) {
            throw new HibernateException("Operation with id: " + storageId
                    + " was filed in method filesList(long id) from class " + StorageDAO.class.getName());
        }
    }
}