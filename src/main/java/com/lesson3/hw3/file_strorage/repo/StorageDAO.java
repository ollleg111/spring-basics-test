package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.constants.Constants;
import com.lesson3.hw3.file_strorage.model.Storage;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO extends GeneralDAO<Storage> {

    public StorageDAO() {
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
}
