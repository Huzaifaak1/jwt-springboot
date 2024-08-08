package com.example.jwt.jwt.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.jwt.dtos.CreateUserDto;
import com.example.jwt.jwt.models.User;
import com.example.jwt.jwt.repos.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    public User saveUser(CreateUserDto createUserDto) {
        User convertedUser = this.modelMapper.map(createUserDto, User.class);
        User savedUser = this.userRepo.save(convertedUser);
        return savedUser;
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = this.userRepo.findByEmail(email);
        User user = this.modelMapper.map(optionalUser, User.class);
        return user;
    }

    public List<User> getAllUsers(){
        List<User> user = this.userRepo.findAll();
        if(user.size()>0){
            return user;
        }else{
            return null;
        }
    }
}
