package com.test.task.request.user;

import com.test.task.entity.user.UserRole;

import javax.validation.constraints.NotNull;

public class UpdateUserRequest {
    private String login;

    private String password;

    private UserRole role;

    public UpdateUserRequest() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
