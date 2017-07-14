package com.example.api.controller.login;

import com.example.api.core.DefaultParams;
import com.example.api.core.ResponseMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydd on 2017-07-13.
 */
@RestController
public class LoginController{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login/{id}", method = RequestMethod.GET)
    public Map<String, Object> loginPros(@PathVariable("id") String id, DefaultParams defaultParams, Map<String, Object> resMap, HttpSession session) throws Exception {

        if(StringUtils.equals(id, "whydda")){
            session.setAttribute("ROLE", "USER");
            Map<String, String> userMap = new HashMap();
            userMap.put("userId", id);
            session.setAttribute("userMap", userMap);
        }

        return new ResponseMap().ok();
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public Map<String, Object> logout(HttpServletRequest request) throws Exception{
        request.getSession().invalidate();
        return new ResponseMap().ok("로그아웃 처리 되었습니다.");
    }

    @RequestMapping(value="/test/log", method = RequestMethod.GET)
    public Map<String, Object> test(DefaultParams defaultParams, Map<String, Object> resMap, HttpSession session) throws Exception {

        LOGGER.debug("로그 잘 남는것입니까?");
        LOGGER.debug("---------------------");
        return new ResponseMap().ok(defaultParams.getMap());
    }
}
