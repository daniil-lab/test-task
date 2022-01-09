package com.test.task.configuration;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserCred implements UserDetails {
    private String login;

    private String password;

    private UserRole role;

    public static AuthUserCred createUserAuthDetails(User user) {
        AuthUserCred c = new AuthUserCred();
        c.login = user.getLogin();
        c.password = user.getPassword();
        c.role = user.getRole();

        return c;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> role = new ArrayList<>();

        role.add(this.role);

        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
