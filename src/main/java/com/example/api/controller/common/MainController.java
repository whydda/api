package com.example.api.controller.common;

import com.example.api.core.DefaultParams;
import com.example.api.core.ResponseMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by whydd on 2017-07-13.
 */
@RestController
public class MainController {

    @GetMapping(value = "/user/{id}/main")
    public Map<String, Object> main(@PathVariable("id") String id, DefaultParams defaultParams, Map<String, Object> resMap) throws Exception {
        return new ResponseMap().ok();
    }
}
