package com.lesson3.hw3.file_storage.service;

import com.lesson3.hw3.file_storage.exceptions.BadRequestException;
import com.lesson3.hw3.file_storage.model.File;
import com.lesson3.hw3.file_storage.repo.FileDAO;
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

    /*
    Имя файла не может быть больше 10 символов, то есть файл с таким именем не может быть создан
     */
    public File save(File file) throws HibernateException {
        if (file.getName().length() > 10)
            throw new BadRequestException("The name of file: " + file.getName() +
                    "is too large. Try to write smaller file's name. in method " +
                    "save(File file) from class " +
                    FileService.class.getName());

        return fileDAO.save(file);
    }

    public File update(File file) throws HibernateException {
        return fileDAO.update(file);
    }

    public void delete(long id) throws HibernateException {
        fileDAO.delete(id);
    }

    /*
    Проверка наличия нужного файла по id
     */
    public File findById(long id) throws RuntimeException {
        File file = fileDAO.findById(id);
        if (file == null) throw new BadRequestException("File with id: " + id +
                " does not exist in method findById(long id) from class " +
                FileService.class.getName());
        return file;
    }
}
