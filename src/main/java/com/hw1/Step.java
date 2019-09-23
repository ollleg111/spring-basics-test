package com.hw1;

import java.util.Map;

@org.springframework.stereotype.Service
public class Step {

    private long id;
    private Service serviceFrom;
    private Service serviceTo;
    private Map paramsServiceFrom;
    private Map getParamsServiceTo;

    public Step() {
    }

    public Step(long id, Service serviceFrom, Service serviceTo, Map paramsServiceFrom, Map getParamsServiceTo) {
        this.id = id;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
        this.paramsServiceFrom = paramsServiceFrom;
        this.getParamsServiceTo = getParamsServiceTo;
    }

    public long getId() {
        return id;
    }

    public Service getServiceFrom() {
        return serviceFrom;
    }

    public Service getServiceTo() {
        return serviceTo;
    }

    public Map getParamsServiceFrom() {
        return paramsServiceFrom;
    }

    public Map getGetParamsServiceTo() {
        return getParamsServiceTo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setServiceFrom(Service serviceFrom) {
        this.serviceFrom = serviceFrom;
    }

    public void setServiceTo(Service serviceTo) {
        this.serviceTo = serviceTo;
    }

    public void setParamsServiceFrom(Map paramsServiceFrom) {
        this.paramsServiceFrom = paramsServiceFrom;
    }

    public void setGetParamsServiceTo(Map getParamsServiceTo) {
        this.getParamsServiceTo = getParamsServiceTo;
    }
}
