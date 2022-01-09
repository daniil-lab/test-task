package com.test.task.dto.user;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;

import java.util.UUID;

public class UserDTO {
    private UUID id;

    private String login;

    private UserRole role;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
