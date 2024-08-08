package com.example.jwt.jwt.dtos;

import lombok.Data;

@Data
public class CreateUserDto {

    String name;
    String email;
    String password;
    String bio;
}
