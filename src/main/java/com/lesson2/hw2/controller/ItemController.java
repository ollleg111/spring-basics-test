package com.lesson2.hw2.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson2.hw2.model.Item;
import com.lesson2.hw2.service.ItemService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    private Item item;

    @RequestMapping(method = RequestMethod.POST,
            value = "/save",
            produces = "text/plan")
    public @ResponseBody
    String callSave(InputStream data) throws HibernateException {
        try {
            item = itemService.save(new ObjectMapper().readValue(data, Item.class));
            return "Item with id: "
                    + item.getId()
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
            value = "/get",
            produces = "text/plan")
    public @ResponseBody
    String callFind(InputStream data) throws HibernateException {
        try {
            item = itemService.findById(new ObjectMapper().readValue(data, Item.class).getId());
            return "Item with id: "
                    + item.getId()
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
            value = "/update",
            produces = "text/plan")
    public @ResponseBody
    String callUpdate(InputStream data) throws HibernateException {
        try {
            return "Item with id: "
                    + itemService.update(new ObjectMapper().readValue(data, Item.class)).getId()
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
            value = "/delete",
            produces = "text/plan")
    public @ResponseBody
    String callDelete(InputStream data) throws HibernateException {
        try {
            itemService.delete(new ObjectMapper().readValue(data, Item.class).getId());
            return "Item was deleting";
        } catch (JsonParseException e) {
            return e.getMessage();
        } catch (JsonMappingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}