package ru.currency.restful.entity;

import java.util.LinkedHashMap;

public class Gif {
    private LinkedHashMap<String, Object> data;
    private LinkedHashMap<String, String> meta;

    public Gif(LinkedHashMap<String, Object> data, LinkedHashMap<String, String> meta) {
        this.data = data;
        this.meta = meta;
    }

    public LinkedHashMap<String, Object> getData() {
        return data;
    }

    public LinkedHashMap<String, String> getMeta() {
        return meta;
    }
}
