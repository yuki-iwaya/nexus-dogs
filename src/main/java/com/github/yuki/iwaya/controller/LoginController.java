package com.github.yuki.iwaya.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yuki.iwaya.config.JwtProducer;
import com.github.yuki.iwaya.model.User;
import com.github.yuki.iwaya.model.LoginRequestModel.LoginRequest;
import com.github.yuki.iwaya.model.LoginResponseModel.LoginResponse;
import com.github.yuki.iwaya.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProducer jwtProducer;
    
    
    @PostMapping
    public ResponseEntity<LoginResponse> postMethodName(@RequestBody LoginRequest loginRequest) {
    
        User user = userService.getUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return null;
        }

        String token = jwtProducer.createToken(loginRequest.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Success");
        loginResponse.setToken(token);

        return ResponseEntity.ok(loginResponse);
    }
    
}
