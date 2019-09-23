package com.hw1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {

    private long id;
    private String name;
    private List paramsToCall;

    public Services() {

    }

    public Services(long id, String name, List paramsToCall) {
        this.id = id;
        this.name = name;
        this.paramsToCall = paramsToCall;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List getParamsToCall() {
        return paramsToCall;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParamsToCall(List paramsToCall) {
        this.paramsToCall = paramsToCall;
    }
}
