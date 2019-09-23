package com.hw1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Route {

    private String id;
    private List steps;

    public Route() {

    }

    public Route(String id, List steps) {
        this.id = id;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public List getSteps() {
        return steps;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }
}
