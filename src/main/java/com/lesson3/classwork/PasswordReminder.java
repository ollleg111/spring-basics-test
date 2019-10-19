package com.lesson3.classwork;

import org.springframework.beans.factory.annotation.Autowired;

public class PasswordReminder {

    private DbConnector db;

    @Autowired
    public PasswordReminder(DbConnector db) {
        this.db = db;
    }

    public void sendPassword() {
        //some logic
    }

//    @Autowired
//    public void setDb(DbConnector db) {
//        this.db = db;
//    }
}
