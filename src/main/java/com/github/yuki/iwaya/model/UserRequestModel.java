package com.github.yuki.iwaya.model;

import lombok.Data;

public class UserRequestModel {
    @Data
    public static class UserRequest {
        private String username;
        private String password;
        private String email;
        private User.Role role;
    }

}
