package com.lesson3.hw3.file_storage.demo;

import com.lesson3.hw3.file_storage.controller.StorageController;
import com.lesson3.hw3.file_storage.model.File;
import com.lesson3.hw3.file_storage.model.Storage;
import com.lesson3.hw3.file_storage.repo.FileDAO;
import com.lesson3.hw3.file_storage.repo.StorageDAO;
import com.lesson3.hw3.file_storage.service.FileService;
import com.lesson3.hw3.file_storage.service.StorageService;
import com.lesson3.hw3.file_storage.util.HibernateUtil;

public class Test {

    private final static FileService fileService = new FileService(new FileDAO(new HibernateUtil()));
    private final static StorageService storageService = new StorageService(new StorageDAO(new HibernateUtil()),
            new FileDAO(new HibernateUtil()));

    private final static StorageController storageController = new StorageController(storageService, fileService);

    public static void main(String[] args) {

        File file0 = new File("aaa", "txt", 50, null);
        File file1 = new File("bbb", "mp3", 150, null);
        File file2 = new File("ccc", "jpg", 250, null);

        Storage storage0 = new Storage(new String[]{"txt", "jpg", "mp3"}, "UA", 1000);
        Storage storage1 = new Storage(new String[]{"txt", "jpg", "mp3"}, "UK", 1000);
        Storage storage2 = new Storage(new String[]{"txt", "jpg", "mp3"}, "US", 1000);


        file0.setId(11);
        file1.setId(12);
        file2.setId(13);

        storage0.setId(100);
        storage1.setId(101);
        storage2.setId(102);

        try {
            storageController.transferAll(storage0,storage1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
