package com.example.api.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydd on 2019-03-03.
 */
public class DefaultParams{
    private HashMap<String, Object> map;

    public Map<String, Object> getMap(){
        return this.map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }

    public void put(String key, Object value){
        this.map.put(key, value);
    }

    public String get(String key){
        return String.valueOf(this.map.get(key));
    }
}