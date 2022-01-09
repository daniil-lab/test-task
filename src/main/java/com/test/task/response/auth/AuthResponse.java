package com.test.task.response.auth;

import com.test.task.dto.user.UserDTO;
import com.test.task.entity.user.User;

public class AuthResponse {
    private String token;

    private UserDTO user;

    public AuthResponse() {}

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = new UserDTO(user);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
