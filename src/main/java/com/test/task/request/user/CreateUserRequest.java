package com.test.task.request.user;

import com.test.task.entity.user.UserRole;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotNull
    @Length(min = 1)
    private String login;

    @NotNull
    @Length(min = 1)
    private String password;

    @NotNull
    private UserRole role;

    public CreateUserRequest() {}

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
