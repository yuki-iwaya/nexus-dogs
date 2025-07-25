package com.github.yuki.iwaya.model;

import lombok.Data;

public class LoginResponseModel {
    
    @Data
    public static class LoginResponse {
        private String token;
        private String message;
    }

}
