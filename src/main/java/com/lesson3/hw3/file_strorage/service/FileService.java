package com.lesson3.hw3.file_strorage.service;

import com.lesson3.hw3.file_strorage.exceptions.BadRequestException;
import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.repo.FileDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private FileDAO fileDAO;

    @Autowired
    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public File save(File file) throws HibernateException {
        return fileDAO.save(file);
    }

    public File update(File file) throws HibernateException {
        return fileDAO.update(file);
    }

    public void delete(long id) throws HibernateException {
        fileDAO.delete(id);
    }

    public File findById(long id) throws Exception {
        File file = fileDAO.findById(id);
        if (file == null) throw new BadRequestException("File with id: " + id + " do not exist");
        return file;
    }
}
