package com.github.yuki.iwaya.model;

import lombok.Data;

public class UserResponseModel {

    @Data
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
    }
    
}
