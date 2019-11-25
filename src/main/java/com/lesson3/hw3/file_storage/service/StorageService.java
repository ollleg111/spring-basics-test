package com.lesson3.hw3.file_storage.service;

import com.lesson3.hw3.file_storage.exceptions.BadRequestException;
import com.lesson3.hw3.file_storage.model.File;
import com.lesson3.hw3.file_storage.model.Storage;
import com.lesson3.hw3.file_storage.repo.FileDAO;
import com.lesson3.hw3.file_storage.repo.StorageDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    Проверка наличия нужного хранилища по id
     */
    public Storage findById(long id) throws RuntimeException {
        Storage storage = storageDAO.findById(id);
        if (storage == null) throw new BadRequestException("Storage with id: " + id + " does not exist in method " +
                "findById(long id) from class " + StorageService.class.getName());
        return storage;
    }

    /*
    put(Storage storage, File file) - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    delete(Storage storage, File file)
    transferAll(Storage storageFrom, Storage storageTo) - трансфер всех файлов
    transferFile(Storage storageFrom, Storage storageTo, long id) - трансфер файла с хранилища storageFrom по его айди
     */

    public void put(Storage storage, File file) throws BadRequestException {
        findById(file.getId());
        if (storageDAO.findById(storage.getId()) == null) throw new
                BadRequestException("Storage with id: " + storage.getId() +
                "does not exist in method put(Storage storage, File file) from class " +
                StorageService.class.getName());

        /*
        Storage может хранить файлы только поддерживаемого формата
         */
        if (!storageDAO.getFormatFromStorage(storage.getId()).contains(file.getFormat()))
            throw new BadRequestException("Storage with id: " + storage.getId() +
                    " does not support format file with id: " + file.getId() + " in method" +
                    " put(Storage storage, File file) " +
                    " from class " + StorageService.class);

        file.setStorage(storage);
        fileDAO.update(file);
    }

    public void delete(Storage storage, File file) throws BadRequestException {
        findById(file.getId());
        if (storageDAO.findById(storage.getId()) == null) throw new
                BadRequestException("Storage with id: " + storage.getId() +
                "does not exist in method delete(Storage storage, File file) from class " +
                StorageService.class.getName());

        file.setStorage(null);
        fileDAO.update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException {
        /*
        Учитывайте макс размер хранилища
         */
        if (storageDAO.getStorageAmount(storageTo) >= storageDAO.getStorageAmount(storageFrom) +
                storageDAO.getFilledVolume(storageTo.getId()))
            throw new BadRequestException("Do not have needed amount in storageTo in method " +
                    "transferAll(Storage storageFrom, Storage storageTo) from class " +
                    StorageService.class.getName());

        /*
        В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
         */
        if (!Collections.disjoint(
                storageDAO.getIdFileInStorage(storageTo.getId()),
                storageDAO.getIdFileInStorage(storageFrom.getId()))) throw
                new BadRequestException("Storage with id: " + storageFrom.getId() +
                        " already contains file from storage with id: " + storageTo.getId() +
                        " in method transferAll(Storage storageFrom, Storage storageTo)");

        /*
         Storage может хранить файлы только поддерживаемого формата
         */
        if (!storageDAO.getFormatFromStorage(storageTo.getId()).containsAll(
                storageDAO.getFormatFromStorage(storageFrom.getId())))
            throw new BadRequestException("Storage with id: " + storageTo.getId() +
                    " does not support format files from storage with id:" + storageFrom.getId() +
                    " transferAll(Storage storageFrom, Storage storageTo) " +
                    " from class " + StorageService.class);

        List<File> fileList = storageDAO.filesList(storageFrom.getId());
        for (File file : fileList) {

            file.setStorage(storageTo);
            fileDAO.update(file);
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws BadRequestException {
        if (storageFrom.getId() != fileDAO.getStorage(id))
            throw new BadRequestException("File with id: " + id + " does not exist in storage with id: " +
                    storageFrom.getId());

        /*
        Учитывайте макс размер хранилища
         */
        if ((storageDAO.getStorageAmount(storageTo) - storageDAO.getFilledVolume(storageTo.getId())) >=
                storageDAO.getFileSize(id))
            throw new BadRequestException("Do not have needed amount in storageTo in method " +
                    "transferFile(Storage storageFrom, Storage storageTo, long id) " +
                    "from class " + StorageService.class.getName());

        /*
        В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
         */
        if (storageDAO.getIdFileInStorage(storageTo.getId()).contains(id)) throw
                new BadRequestException("Storage with id: " + storageTo.getId() +
                        " already contains file with id: " + id +
                        " in method transferFile(Storage storageFrom, Storage storageTo, long id)");

         /*
         Storage может хранить файлы только поддерживаемого формата
          */
        if (!storageDAO.getFormatFromStorage(storageTo.getId()).contains(fileDAO.getFormat(id)))
            throw new BadRequestException("Storage with id: " + storageTo.getId() +
                    " does not support format file with id: " + id + " in method" +
                    " transferFile(Storage storageFrom, Storage storageTo, long id) " +
                    " from class " + StorageService.class);

        File file = fileDAO.findById(id);

        file.setStorage(storageTo);
        fileDAO.update(file);
    }
}
