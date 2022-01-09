package com.test.task.request.auth;

import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    private String login;

    @NotNull
    private String password;

    public AuthRequest() {}

    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

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
}
