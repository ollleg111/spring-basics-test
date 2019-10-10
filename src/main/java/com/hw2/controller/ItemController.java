package com.hw2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw2.model.Item;
import com.hw2.service.ItemService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@Controller
public class ItemController {

    /*
    private long id;
    private String name;
    private Date dateCreated;
    private Date lastUpdateDate;
    private String description;
     */

    @Autowired
    private ItemService itemService;
    /*
    https://www.leveluplunch.com/java/tutorials/014-post-json-to-spring-rest-webservice/
     */

    @RequestMapping(method = RequestMethod.POST,
            value = "/save",
            produces = "text/plan")
    public @ResponseBody
    String callSave(InputStream data) throws HibernateException {
        try {
            Item item = itemService.save(new ObjectMapper().readValue(data, Item.class));
            return "Item with id: " + item.getId() + " was saved";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
//TODO json data for Postman
    /*
    {
  "name":"jbjbjbjb",
  "dateCreated":"2018-09-29T18:46:19-0700",
  "lastUpdateDate":"2021-09-29T18:46:19-0700",
  "description":"test"
}
     */

//    @RequestMapping(method = RequestMethod.POST,
//            value = "/save",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public Item save(@RequestBody Item item) throws HibernateException {
//        System.out.println("from json input : " + item.getId() + " : " + item.getName());
//        itemService.save(item);
//        return item;
//    }

//    @RequestMapping(method = RequestMethod.POST,
//            value = "/save",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Item> callCreate(@RequestBody Item item) {
//        return new ResponseEntity<>(itemService.save(item), HttpStatus.OK);
//    }

    /*
     @RequestMapping(method = RequestMethod.POST, value = "/save")
    public @ResponseBody
    String callCreate(@RequestParam(value = "name") String name,
                      @RequestParam(value = "date0") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dateCreated,
                      @RequestParam(value = "date1") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date lastUpdateDate,
                      @RequestParam(value = "description") String description)
            throws HibernateException {
        String itemName = String.valueOf(name);
        String des = String.valueOf(description);
        return "The item: " + itemService.save(new Item(itemName, dateCreated, lastUpdateDate, des)) +
                " was saving";
            }
     */
//        TODO работает через Params в постмене
//        http://localhost:8080/save?name=abcdef&date0=04-OCT-19 08:58:47&date1=05-OCT-19 08:58:52&description=asdssddds


    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public @ResponseBody
    String callFind(@RequestParam long id) throws HibernateException {
        return "The item: " + itemService.findById(Long.parseLong(String.valueOf(id))).toString() +
                " was getting";
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/update",
            produces = "text/plan")
    public @ResponseBody
    String callUpdate(InputStream data) throws HibernateException {
        try {
            return "Item with id: " + itemService.update(new ObjectMapper().readValue(data, Item.class)).getId()
                    + " was updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public @ResponseBody
    String callDelete(@RequestParam long id) throws HibernateException {
        itemService.delete(Long.parseLong(String.valueOf(id)));
        return "The item was deleting";
    }
}