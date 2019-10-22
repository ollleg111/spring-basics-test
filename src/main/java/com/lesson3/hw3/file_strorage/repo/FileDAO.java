package com.lesson3.hw3.file_strorage.repo;

import com.lesson3.hw3.file_strorage.constants.Constants;
import com.lesson3.hw3.file_strorage.model.File;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO extends GeneralDAO<File> {

    public FileDAO() {
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

//    public List<File> filesList(long id) throws HibernateException{
//        try (Session session = hibernateUtil.startSession()) {
//            return (T) session.get(typeClass, id);
//        } catch (HibernateException e) {
//            throw new HibernateException("operation with id: " + id
//                    + " was filed in method findById(long id)");
//        }
//    }
}
