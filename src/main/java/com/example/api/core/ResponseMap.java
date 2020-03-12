package com.example.api.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydd on 2017-07-14.
 */
@Slf4j
public class ResponseMap {
    private Map<String, Object> resMap;

    /**
     * 200
     *
     * @return
     */
    public Map<String, Object> ok() throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", "정상처리 되었습니다.");
        return this.responseMap();
    }

    public Map<String, Object> ok(Map<String, Object> resMap) throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("resMap", resMap);
        this.resMap.put("code", HttpStatus.OK.value());
        this.resMap.put("message", "정상처리 되었습니다.");
        return this.responseMap();
    }

    /**
     * 401
     *
     * @return
     */
    public Map<String, Object> unauthorized() throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.UNAUTHORIZED.value());
        this.resMap.put("message", "잘못된 경로로 접근하였습니다. ");
        return this.responseMap();
    }

    /**
     * 500
     *
     * @return
     */
    public Map<String, Object> internalServerError() throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.resMap.put("message", "서버에서 에러가 발생하였습니다.</br>관리자에게 문의하여 주십시오.");
        return this.responseMap();
    }

    public Map<String, Object> commonError(int code, String message, Map<String, Object> dataMap) throws Exception {
        this.resMap = new HashMap<>();
        this.resMap.put("code", code);
        this.resMap.put("message", message);
        this.resMap.putAll(dataMap);
        return this.responseMap();
    }

    private Map<String, Object> responseMap() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.resMap);
        return objectMapper.readValue(json, HashMap.class);
    }

    private void responseJson(int code, String message) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code);
            jsonObject.put("message", message);
        } catch (JSONException je) {
            jsonObject.put("code", "500");
            jsonObject.put("message", "서버에서 에러가 발생하였습니다.</br>관리자에게 문의하여 주십시오.");
            log.error("JSON 파싱오류", je);
        }

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        sra.getResponse().setCharacterEncoding(StandardCharsets.UTF_8.name());
        sra.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
        sra.getResponse().getWriter().print(jsonObject);
        sra.getResponse().getWriter().flush();
    }
}
