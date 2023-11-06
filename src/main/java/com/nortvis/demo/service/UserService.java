package com.nortvis.demo.service;

import com.nortvis.demo.dto.CreateUserDto;
import com.nortvis.demo.dto.UserDto;

public interface UserService {
    UserDto getUser(Long userId);
    UserDto getUser(String userName);
    UserDto saveUser(CreateUserDto user);
}
