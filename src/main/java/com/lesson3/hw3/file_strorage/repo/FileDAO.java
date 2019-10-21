package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.constants.Constants;
import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO extends GeneralDAO<File> {

    @Autowired
    public FileDAO(HibernateUtil hibernateUtil) {
        setTypeClass(File.class);
        setHibernateUtil(hibernateUtil);
    }

    @Override
    public File save(File o) throws HibernateException {
        return super.save(o);
    }

    @Override
    public File update(File o) throws HibernateException {
        return super.update(o);
    }

    public void delete(long id) throws HibernateException {
        super.delete(id, Constants.FILE_REQUEST_DELETE);
    }

    @Override
    public File findById(long id) throws HibernateException {
        return super.findById(id);
    }
}
