package ru.currency.restful.entity;

import java.util.TreeMap;

public class BaseObject {
    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private TreeMap<String, Double> rates;

    public BaseObject(String disclaimer, String license, String timestamp, String base, TreeMap<String, Double> rates) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public TreeMap<String, Double> getRates() {
        return rates;
    }
}
