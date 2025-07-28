package com.github.yuki.iwaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.yuki.iwaya.model.User;
import com.github.yuki.iwaya.model.UserRequestModel.UserRequest;
import com.github.yuki.iwaya.model.UserResponseModel.UserResponse;
import com.github.yuki.iwaya.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request){
        
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("exists by username");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("exist by email");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        return response;
    }

    public User getUser(String email, String password){
        User user = userRepository.findByEmail(email);
        System.out.println(password + " : " + user.getPassword());
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }

    public User getUserById(Long Id){

        User user = userRepository.findById(Id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

}
