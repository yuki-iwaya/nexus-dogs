package com.github.yuki.iwaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yuki.iwaya.config.PasswordEncoderConfig;
import com.github.yuki.iwaya.dto.UserDto.UserRequest;
import com.github.yuki.iwaya.dto.UserDto.UserResponse;
import com.github.yuki.iwaya.model.User;
import com.github.yuki.iwaya.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    public UserResponse register(UserRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("exists by username");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("exist by email");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.passwordEncoder().encode(request.getPassword()));
        user.setEmail(request.getEmail());

        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        return response;
    }
}
