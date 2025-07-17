package com.github.yuki.iwaya.dto;

import lombok.Data;

public class UserDto {
    @Data
    public static class UserRequest {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
    }
}
