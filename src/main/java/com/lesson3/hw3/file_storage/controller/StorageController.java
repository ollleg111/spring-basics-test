package com.lesson3.hw3.file_storage.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.lesson3.hw3.file_storage.model.File;
import com.lesson3.hw3.file_storage.model.Storage;
import com.lesson3.hw3.file_storage.service.FileService;
import com.lesson3.hw3.file_storage.service.StorageService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/storage")
public class StorageController {
    private StorageService storageService;
    private FileService fileService;
    private Storage storage;

    @Autowired
    public StorageController(StorageService storageService, FileService fileService) {
        this.storageService = storageService;
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/saveStorage",
            produces = "text/plan")
    public @ResponseBody
    String callSave(InputStream data) throws HibernateException {
        try {
            storage = storageService.save(new ObjectMapper().readValue(data, Storage.class));
            return "Storage with id: "
                    + storage.getId()
                    + " was saved";
        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/getStorage",
            produces = "text/plan")
    public @ResponseBody
    String callFind(InputStream data) throws HibernateException {
        try {
            storage = storageService.findById(new ObjectMapper().readValue(data, Storage.class).getId());
            return "Storage with id: "
                    + storage.getId()
                    + " was getting";
        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/updateStorage",
            produces = "text/plan")
    public @ResponseBody
    String callUpdate(InputStream data) throws HibernateException {
        try {
            return "Storage with id: "
                    + storageService.update(new ObjectMapper().readValue(data, Storage.class)).getId()
                    + " was updated";
        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/deleteStorage",
            produces = "text/plan")
    public @ResponseBody
    String callDelete(InputStream data) throws HibernateException {
        try {
            storageService.delete(new ObjectMapper().readValue(data, Storage.class).getId());
            return "Storage was deleting";
        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /*
    put(Storage storage, File file) - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    delete(Storage storage, File file)
    transferAll(Storage storageFrom, Storage storageTo) - трансфер всех файлов
    transferFile(Storage storageFrom, Storage storageTo, long id) - трансфер файла с хранилища storageFrom по его айди
     */

    @RequestMapping(method = RequestMethod.PUT,
            value = "/putFile",
            produces = "text/plan")
    public @ResponseBody
    String put(InputStream data) throws HibernateException {
        try {
            File file = new ObjectMapper().readValue(data, File.class);
            Storage storage = file.getStorage();
            storageService.put(storage, file);
            return "File with id: " + file.getId() + " was added to Storage: " + storage.getId();

        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/deleteFile",
            produces = "text/plan")
    public @ResponseBody
    String delete(InputStream data) throws HibernateException {
        try {
            File file = new ObjectMapper().readValue(data, File.class);
            Storage storage = file.getStorage();
            storageService.delete(storage, file);
            return "File with id: " + file.getId() + " was deleted from Storage: " + storage.getId();

        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

//    ObjectMapper mapper = new ObjectMapper();
//    MapType type = mapper.getTypeFactory().constructMapType(
//            Map.class, String.class, Object.class);
//    Map<String, Object> data = mapper.readValue(jsonString, type);

//    @PutMapping("/transfer/All")
//    public void transferAll(@RequestParam Storage storageFrom,
//                            @RequestParam Storage storageTo) throws HibernateException {
//        storageService.transferAll(storageFrom, storageTo);
//    }
//
//    @PutMapping("/transfer/{id}")
//    public void transferFile(@RequestParam Storage storageFrom,
//                             @RequestParam Storage storageTo,
//                             @PathVariable long id) throws HibernateException {
//        storageService.transferFile(storageFrom, storageTo, id);
//    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/transferAll",
            produces = "text/plan")
    public @ResponseBody
    String transferAll(InputStream data) throws HibernateException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Storage.class);
            Map<String, Storage> dataMap = mapper.readValue(data, type);

            List<Storage> arrayList = new ArrayList<>();
            for (Map.Entry<String, Storage> entry : dataMap.entrySet()) {
                arrayList.add(entry.getValue());
            }
            storageService.transferAll(arrayList.get(0), arrayList.get(1));

            return "All from storage with id: " + arrayList.get(0).getId() +
                    " was transferred to storage with id: " + arrayList.get(1).getId();

        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/transferFile",
            produces = "text/plan")
    public @ResponseBody
    String transferFile(InputStream data) throws HibernateException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
            Map<String, Object> dataMap = mapper.readValue(data, type);

            List<Object> arrayList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                arrayList.add(entry.getValue());
            }
            Storage storageFrom = (Storage) arrayList.get(0);
            Storage storageTo = (Storage) arrayList.get(1);
            Long id = (Long) arrayList.get(2);

            storageService.transferFile(storageFrom, storageTo, id);

            return "File with id: " + id + " moved from storage with id: " + storageFrom.getId() +
                    " was transferred to storage with id: " + storageTo.getId();

        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
