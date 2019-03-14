package com.example.api.controller.login;

import com.example.api.core.DefaultParams;
import com.example.api.core.ResponseMap;
import com.example.api.service.common.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by whydd on 2017-07-13.
 */
@Slf4j
@RestController
public class LoginController{

    private CommonService CommonService;

    @Autowired
    public LoginController(CommonService commonService){
        this.CommonService = commonService;
    }

    @GetMapping(value="/login/{id}")
    public Map<String, Object> loginPros(@PathVariable("id") String id, DefaultParams defaultParams, ModelMap resMap) throws Exception {
        List<Map<String, Object>> userList = CommonService.selectTest();
        resMap.addAttribute("passwd", defaultParams.get("passwd"));
        resMap.addAttribute("id", id);
        resMap.addAttribute("params", defaultParams.getMap());
        resMap.addAttribute("userList", userList);
        return new ResponseMap().ok("환영합니다.", resMap);
    }


    @GetMapping(value="/logout")
    public Map<String, Object> logout(HttpServletRequest request) throws Exception{
        request.getSession().invalidate();
        return new ResponseMap().ok("로그아웃 처리 되었습니다.");
    }

    @GetMapping(value="/test/log")
    public Map<String, Object> test(DefaultParams defaultParams, Map<String, Object> resMap, HttpSession session) throws Exception {

        log.debug("로그 잘 남는것입니까?");
        log.debug("---------------------");
        return new ResponseMap().ok(defaultParams.getMap());
    }
}
