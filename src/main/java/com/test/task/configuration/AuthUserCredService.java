package com.test.task.configuration;

import com.test.task.entity.user.User;
import com.test.task.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthUserCredService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public AuthUserCred loadUserByUsername(String login) {
        User user = userService.getUserByLogin(login);

        return AuthUserCred.createUserAuthDetails(user);
    }
}