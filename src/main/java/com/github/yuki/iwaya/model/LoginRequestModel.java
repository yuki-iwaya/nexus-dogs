package com.github.yuki.iwaya.model;

import lombok.Data;

public class LoginRequestModel {
    
    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

}
