package com.nortvis.demo.service;

import com.nortvis.demo.dto.CreateUserDto;
import com.nortvis.demo.dto.UserDto;
import com.nortvis.demo.entity.User;

public interface UserService {
    UserDto getUser(Long userId);
    UserDto getUser(String userName);
    UserDto saveUser(CreateUserDto user);
    String getLoggedInUserName();
    User updateUser(User user);
    User getUserEntity(String userName);
}
