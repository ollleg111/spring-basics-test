package com.lesson3.hw3.file_strorage.service;

import com.lesson3.hw3.file_strorage.exceptions.BadRequestException;
import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.model.Storage;
import com.lesson3.hw3.file_strorage.repo.FileDAO;
import com.lesson3.hw3.file_strorage.repo.StorageDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {
    private StorageDAO storageDAO;
    private FileDAO fileDAO;

    @Autowired
    public StorageService(StorageDAO storageDAO, FileDAO fileDAO) {
        this.storageDAO = storageDAO;
        this.fileDAO = fileDAO;
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

    /*
    Проверка наличия нужного хранидлища по id
     */
    public Storage findById(long id) throws RuntimeException {
        Storage storage = storageDAO.findById(id);
        if (storage == null) throw new BadRequestException("Storage with id: " + id + " does not exist in method " +
                "findById(long id) from class " +
                StorageService.class.getName());
        return storage;
    }

    /*
    put(Storage storage, File file) - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    delete(Storage storage, File file)
    transferAll(Storage storageFrom, Storage storageTo) - трансфер всех файлов
    transferFile(Storage storageFrom, Storage storageTo, long id) - трансфер файла с хранилища storageFrom по его айди
     */

    public void put(Storage storage, File file) throws BadRequestException {
        validateExist(storage, file);

        file.setStorage(storage);
        fileDAO.update(file);
    }

    public void delete(Storage storage, File file) throws BadRequestException {
        validateExist(storage, file);

        file.setStorage(null);
        fileDAO.update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException {
        if (storageDAO.getStorageAmount(storageTo) > storageDAO.getStorageAmount(storageFrom))
            throw new BadRequestException("Do not have needed amount in storageTo in method " +
                    "transferAll(Storage storageFrom, Storage storageTo) from class " +
                    StorageService.class.getName());

        List<File> fileList = storageDAO.filesList(storageFrom.getId());
        for (File file : fileList) {
            file.setStorage(storageTo);
            fileDAO.update(file);
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws BadRequestException {
        if ((storageDAO.getStorageAmount(storageTo) + fileDAO.findById(id).getSize()) >
                storageDAO.getStorageAmount(storageFrom))
            throw new BadRequestException("Do not have needed amount in storageTo in method " +
                    "transferFile(Storage storageFrom, Storage storageTo, long id) " +
                    StorageService.class.getName());
        File file = fileDAO.findById(id);
        file.setStorage(storageTo);
        fileDAO.update(file);
    }

    private void validateExist(Storage storage, File file) throws BadRequestException {
        findById(file.getId());

        if (storageDAO.findById(storage.getId()) == null) throw new
                BadRequestException("Storage with id: " + storage.getId() +
                "does not exist in method validateExist(Storage storage, File file) from class " +
                StorageService.class.getName());
    }
}
