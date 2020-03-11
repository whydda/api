package com.example.api.service.mapper;

import com.example.api.core.CommonMap;
import com.example.api.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: whydda
 * Date: 2020-03-11
 * Time: 오후 8:28
 */
@Repository("userMapper")
public interface UserMapper {
    Optional<UserDto> selectUser(String userId);
}
