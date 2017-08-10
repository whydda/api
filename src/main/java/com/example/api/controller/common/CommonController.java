package com.example.api.controller.common;

import com.example.api.core.DefaultParams;
import com.example.api.core.ResponseMap;
import com.example.api.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by whydda on 2017-08-10.
 */
@RestController
public class CommonController {
    @Autowired
    CommonService commonService;

    @RequestMapping(value="/test")
    public Map<String, Object> getUser(DefaultParams defaultParams, ModelMap model) throws Exception {
        List<Map<String, Object>> resultList = commonService.selectTest();
        model.addAttribute("resultList", resultList);
        return new ResponseMap().ok(model);
    }
}
