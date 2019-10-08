package com.hw2.controller;

import com.hw2.model.Item;
import com.hw2.service.ItemService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public @ResponseBody
    String callCreate(@RequestParam long id,
                      @RequestParam String name,
                      @RequestParam(value = "date0") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dateCreated,
                      @RequestParam(value = "date1") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date lastUpdateDate,
                      @RequestParam String description)
            throws HibernateException {
        long itemId = Long.parseLong(String.valueOf(id));
        String itemName = String.valueOf(name);
        String des = String.valueOf(description);

        return "The item: " + itemService.save(new Item(itemId, itemName, dateCreated, lastUpdateDate, des)) +
                " was saving";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public @ResponseBody
    String callFind(@RequestParam long id) throws HibernateException {
        return "The item: " + itemService.findById(Long.parseLong(String.valueOf(id))).toString() +
                " was getting";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody
    String callUpdate(@RequestParam long id,
                      @RequestParam String name,
                      @RequestParam(value = "date0") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date dateCreated,
                      @RequestParam(value = "date1") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date lastUpdateDate,
                      @RequestParam String description)
            throws HibernateException {
        long itemId = Long.parseLong(String.valueOf(id));
        String itemName = String.valueOf(name);
        String des = String.valueOf(description);

        return "The item: " + itemService.update(new Item(itemId, itemName, dateCreated, lastUpdateDate, des)) +
                " was updating";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public @ResponseBody
    String callDelete(@RequestParam long id) throws HibernateException {
        itemService.delete(Long.parseLong(String.valueOf(id)));
        return "The item was deleting";
    }
}