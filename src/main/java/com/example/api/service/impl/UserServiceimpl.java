package com.example.api.service.impl;

import com.example.api.core.CommonMap;
import com.example.api.dto.UserDto;
import com.example.api.service.UserService;
import com.example.api.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: whydda
 * Date: 2020-03-11
 * Time: 오후 8:26
 */
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto selectUser(String userId) {
        return userMapper.selectUser(userId).orElse(null);
    }

}
