package com.example.jwt.jwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.jwt.dtos.CreateUserDto;
import com.example.jwt.jwt.dtos.LoginDto;
import com.example.jwt.jwt.models.User;
import com.example.jwt.jwt.security.JwtAuthResponse;
import com.example.jwt.jwt.security.JwtTokenHelper;
import com.example.jwt.jwt.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        // Check if user exists;
        User user = this.userService.findByEmail(loginDto.getUsername());
        // Now, check if credentials are valid.
        if (loginDto.getUsername().equals(user.getEmail()) && loginDto.getPassword().equals(user.getPassword())) {
            System.out.println("here");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginDto.getUsername());
            String token = this.jwtTokenHelper.generateToken(userDetails);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);
            return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("register")
    public Map<String, String> register(@RequestBody CreateUserDto registerDto) {
        Map<String, String> resp = new HashMap<>();
        User user = this.userService.saveUser(registerDto);
        if (user != null && user.getId() != null) {
            resp.put("message", "success");
            return resp;
        } else {
            resp.put("message", "failed");
            return resp;
        }

    }

    @GetMapping("users/all")
    public List<User> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return users;
    }
    

}
