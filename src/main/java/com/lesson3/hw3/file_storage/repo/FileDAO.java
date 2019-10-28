package com.lesson3.hw3.file_storage.repo;

import com.lesson3.hw3.file_storage.util.HibernateUtil;
import com.lesson3.hw3.file_storage.constants.Constants;
import com.lesson3.hw3.file_storage.model.File;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO extends GeneralDAO<File> {

    @Autowired
    public FileDAO(HibernateUtil hibernateUtil) {
        setHibernateUtil(hibernateUtil);
        setTypeClass(File.class);
    }

    @Override
    public File save(File file) throws HibernateException {
        return super.save(file);
    }

    @Override
    public File update(File file) throws HibernateException {
        return super.update(file);
    }

    public void delete(long id) throws HibernateException {
        super.delete(id, Constants.FILE_REQUEST_DELETE);
    }

    @Override
    public File findById(long id) throws HibernateException {
        return super.findById(id);
    }
}
