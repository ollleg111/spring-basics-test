package com.lesson2.hw2.service;

import com.lesson2.hw2.model.Item;
import com.lesson2.hw2.repo.ItemDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public Item save(Item item) throws HibernateException {
        return itemDAO.save(item);
    }

    public Item findById(long id) throws HibernateException {
        return itemDAO.findById(id);
    }

    public Item update(Item item) throws HibernateException {
        return itemDAO.update(item);
    }

    public void delete(long id) throws HibernateException {
        itemDAO.delete(id);
    }
}
