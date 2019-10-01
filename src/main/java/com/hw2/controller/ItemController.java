package com.hw2.controller;

import com.hw2.model.Item;
import com.hw2.service.ItemService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    //TODO
    Item item = new Item();

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public @ResponseBody
    String callCreate() throws HibernateException {
        itemService.save(item);
        return "was saving";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text/plain")
    public @ResponseBody
    String callRead() throws HibernateException{
        itemService.findById(item.getId());
        return "was getting";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody
    String callUpdate() throws HibernateException{
        itemService.update(item);
        return "was updating";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String callDelete() throws HibernateException{
        itemService.delete(item.getId());
        return "was deleting";
    }

}