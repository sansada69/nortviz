package com.nortvis.demo.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String userName;
    private String password;
    private String name;
}
