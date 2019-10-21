package com.lesson3.hw3.file_strorage.controller;

import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.model.Storage;
import com.lesson3.hw3.file_strorage.service.FileService;
import com.lesson3.hw3.file_strorage.service.StorageService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private FileService fileService;
    private StorageService storageService;

    @Autowired
    public MainController(FileService fileService, StorageService storageService) {
        this.fileService = fileService;
        this.storageService = storageService;
    }

    /*
    put(Storage storage, File file) - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    delete(Storage storage, File file)
    transferAll(Storage storageFrom, Storage storageTo) - трансфер всех файлов
    transferFile(Storage storageFrom, Storage storageTo, long id) - трансфер файла с хранилища storageFrom по его айди
     */

    public void put(Storage storage, File file) throws Exception {
        storageService.put(storage, file);
    }

    public void delete(Storage storage, File file) throws Exception {
        storageService.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        storageService.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        storageService.transferFile(storageFrom, storageTo, id);
    }

    /*
    CRUD for File
     */
    public File saveFile(File file) throws HibernateException {
        return fileService.save(file);
    }

    public File updateFile(File file) throws HibernateException {
        return fileService.update(file);
    }

    public void deleteFile(long id) throws HibernateException {
        fileService.delete(id);
    }

    public File findFileById(long id) throws HibernateException {
        return fileService.findById(id);
    }


    /*
    CRUD for Storage
     */
    public Storage saveStorage(Storage storage) throws HibernateException {
        return storageService.save(storage);
    }

    public Storage updateStorage(Storage storage) throws HibernateException {
        return storageService.update(storage);
    }

    public void deleteStorage(long id) throws HibernateException {
        storageService.delete(id);
    }

    public Storage findStorageById(long id) throws HibernateException {
        return storageService.findById(id);
    }
}
