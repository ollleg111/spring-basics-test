package com.lesson3.hw3.model;

import java.util.Arrays;

public class Storage {
    private long id;
    private String [] formatSupported;
    private String storageCountry;
    private long storageSize;

    public Storage() {
    }

    public Storage(long id, String[] formatSupported, String storageCountry, long storageSize) {
        this.id = id;
        this.formatSupported = formatSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public Storage(String[] formatSupported, String storageCountry, long storageSize) {
        this.formatSupported = formatSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatSupported() {
        return formatSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFormatSupported(String[] formatSupported) {
        this.formatSupported = formatSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatSupported=" + Arrays.toString(formatSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
