package com.lesson3.hw3.file_strorage.demo;

import com.lesson3.hw3.file_strorage.config.HibernateUtil;
import com.lesson3.hw3.file_strorage.controller.MainController;
import com.lesson3.hw3.file_strorage.model.File;
import com.lesson3.hw3.file_strorage.model.Storage;
import com.lesson3.hw3.file_strorage.repo.FileDAO;
import com.lesson3.hw3.file_strorage.repo.StorageDAO;
import com.lesson3.hw3.file_strorage.service.FileService;
import com.lesson3.hw3.file_strorage.service.StorageService;

public class Demo {

    private static MainController mainController = new MainController(
            new FileService(new FileDAO(new HibernateUtil())),
            new StorageService(new StorageDAO(new HibernateUtil())));

    public static void main(String[] args) {


        File file0 = new File("aaa", "txt", 50, null);
        File file1 = new File("bbb", "mp3", 150, null);
        File file2 = new File("ccc", "jpg", 250, null);

        mainController.saveFile(file0);
        mainController.saveFile(file1);
        mainController.saveFile(file2);

        Storage storage0 = new Storage(new String[]{"txt","jpg","mp3"},"UA",1000);
        Storage storage1 = new Storage(new String[]{"txt","jpg","mp3"},"UK",1000);
        Storage storage2 = new Storage(new String[]{"txt","jpg","mp3"},"US",1000);

        mainController.saveStorage(storage0);
        mainController.saveStorage(storage1);
        mainController.saveStorage(storage2);

        file0.setId(11);
        file1.setId(12);
        file2.setId(13);

        storage0.setId(100);
        storage1.setId(101);
        storage2.setId(102);

        try {
            mainController.put(storage0,file0);
            mainController.put(storage0,file1);
            mainController.put(storage0,file2);

        }catch (Exception e){
            e.getMessage();
        }
    }
}
