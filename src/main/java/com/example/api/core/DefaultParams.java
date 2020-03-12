package com.example.api.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydd on 2019-03-03.
 */
public class DefaultParams {
    private HashMap<String, Object> map;

    public Map<String, Object> getMap() {
        return this.map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }

    public void put(String key, Object value) {
        this.map.put(key, value);
    }

    /**
     * String.valueOf를 사용할 필요 없음
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return String.valueOf(this.map.get(key));
    }

    public Object get(String key) {
        return this.map.get(key);
    }
}
