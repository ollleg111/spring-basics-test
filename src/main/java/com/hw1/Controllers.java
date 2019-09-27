package com.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

    @Autowired
    private Route route;

    @Autowired
    private Services services;

    @Autowired
    private Step step;

    @RequestMapping(method = RequestMethod.GET, value = "/hw1", produces = "text/plain")
    public @ResponseBody
    String callByBean(){

        System.out.println(route.getId());
        System.out.println(route.getSteps());
        System.out.println("-----------------------------------");
        System.out.println(services.getId());
        System.out.println(services.getName());
        System.out.println(services.getParamsToCall());
        System.out.println("-----------------------------------");
        System.out.println(step.getId());
        System.out.println(step.getServicesFrom());
        System.out.println(step.getServicesTo());
        System.out.println(step.getParamsServiceTo());
        System.out.println(step.getParamsServiceFrom());

        return "all is ok";
    }
}
