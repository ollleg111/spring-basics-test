package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.constants.Constants;
import com.lesson3.hw3.file_strorage.model.Storage;
import com.lesson3.hw3.file_strorage.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO extends GeneralDAO<Storage> {

    @Autowired
    public StorageDAO(HibernateUtil hibernateUtil) {
        setTypeClass(Storage.class);
        setHibernateUtil(hibernateUtil);
    }

    @Override
    public Storage save(Storage o) throws HibernateException {
        return super.save(o);
    }

    @Override
    public Storage update(Storage o) throws HibernateException {
        return super.update(o);
    }

    public void delete(long id) throws HibernateException {
        super.delete(id, Constants.STORAGE_REQUEST_DELETE);
    }

    @Override
    public Storage findById(long id) throws HibernateException {
        return super.findById(id);
    }

}
