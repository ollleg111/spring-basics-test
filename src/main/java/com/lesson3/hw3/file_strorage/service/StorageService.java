package com.lesson3.hw3.file_strorage.service;

import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.model.Storage;
import com.lesson3.hw3.file_strorage.repo.StorageDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    private StorageDAO storageDAO;

    @Autowired
    public StorageService(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Storage save(Storage storage) throws HibernateException {
        return storageDAO.save(storage);
    }

    public Storage update(Storage storage) throws HibernateException {
        return storageDAO.update(storage);
    }

    public void delete(long id) throws HibernateException {
        storageDAO.delete(id);
    }

    public Storage findById(long id) throws HibernateException {
        return storageDAO.findById(id);
    }

    public void put(Storage storage, File file) throws Exception {

    }

    public void delete(Storage storage, File file) throws Exception {

    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

    }
}
