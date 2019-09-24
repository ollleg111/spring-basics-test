package com.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Route route;

    @Autowired
    private Services services;

    @Autowired
    private Step step;

    @RequestMapping(method = RequestMethod.GET, value = "/hw1", produces = "text/plain")
    public @ResponseBody
    String callByBean(){

        route.getId();
        route.getSteps();

        services.getId();
        services.getName();
        services.getParamsToCall();

        step.getId();
        step.getGetParamsServiceTo();
        step.getParamsServiceFrom();
        step.getServicesFrom();
        step.getServicesTo();

        return "all is ok";
    }
}
