package com.hw1;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Step {

    private long id;
    private Services servicesFrom;
    private Services servicesTo;
    private Map paramsServiceFrom;
    private Map paramsServiceTo;

    public Step() {
    }

    public Step(long id, Services servicesFrom, Services servicesTo, Map paramsServiceFrom, Map paramsServiceTo) {
        this.id = id;
        this.servicesFrom = servicesFrom;
        this.servicesTo = servicesTo;
        this.paramsServiceFrom = paramsServiceFrom;
        this.paramsServiceTo = paramsServiceTo;
    }

    public long getId() {
        return id;
    }

    public Services getServicesFrom() {
        return servicesFrom;
    }

    public Services getServicesTo() {
        return servicesTo;
    }

    public Map getParamsServiceFrom() {
        return paramsServiceFrom;
    }

    public Map getParamsServiceTo() {
        return paramsServiceTo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setServicesFrom(Services servicesFrom) {
        this.servicesFrom = servicesFrom;
    }

    public void setServicesTo(Services servicesTo) {
        this.servicesTo = servicesTo;
    }

    public void setParamsServiceFrom(Map paramsServiceFrom) {
        this.paramsServiceFrom = paramsServiceFrom;
    }

    public void setParamsServiceTo(Map getParamsServiceTo) {
        this.paramsServiceTo = getParamsServiceTo;
    }
}
