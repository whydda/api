package com.example.api.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydd on 2017-07-14.
 */
public class ResponseMap{
    private Map<String, Object> resMap;

    /**
     * 200
     * @return
     */
    public Map<String, Object> ok() throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", "정상처리 되었습니다.");
        return this.responseMap();
    }

    /**
     * 200
     * @param dataMap
     * @return
     * @throws Exception
     */
    public Map<String, Object> ok(Map<String, Object> dataMap) throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", "정상처리 되었습니다.");
        this.resMap.putAll(dataMap);
        return this.responseMap();
    }

    /**
     * 200
     * @param message
     * @return
     */
    public Map<String, Object> ok(String message) throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", message);
        return this.responseMap();
    }

    /**
     * 200
     * @param message
     * @param dataMap
     * @return
     * @throws Exception
     */
    public Map<String, Object> ok(String message, Map<String, Object> dataMap) throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", message);
        this.resMap.putAll(dataMap);
        return this.responseMap();
    }


    /**
     * 401
     * @return
     */
    public Map<String, Object> unauthorized() throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.UNAUTHORIZED.value());
        this.resMap.put("message", "잘못된 경로로 접근하였습니다. ");
        return this.responseMap();
    }

    /**
     * 401
     * @param message
     * @return
     */
    public Map<String, Object> unauthorized(String message) throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.UNAUTHORIZED.value());
        this.resMap.put("message", message);
        return this.responseMap();
    }

    /**
     * 401
     * @param message
     * @return
     */
    public Map<String, Object> unauthorized(int code, String message) throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", code);
        this.resMap.put("message", message);
        return this.responseMap();
    }

    /**
     * 500
     * @return
     */
    public Map<String, Object> internalServerError() throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.resMap.put("message", "서버에서 에러가 발생하였습니다.</br>관리자에게 문의하여 주십시오.");
        return this.responseMap();
    }

    /**
     * 500
     * @param code
     * @param message
     * @return
     */
    public Map<String, Object> internalServerError(int code, String message) throws Exception{
        this.resMap = new HashMap<>();
        this.resMap.put("code", code);
        this.resMap.put("message", message);
        return this.responseMap();
    }

    private Map<String, Object> responseMap() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.resMap);
        return objectMapper.readValue(json, HashMap.class);
    }
}
