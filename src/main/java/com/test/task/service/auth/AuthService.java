package com.test.task.service.auth;

import com.test.task.configuration.JwtProvider;
import com.test.task.entity.user.User;
import com.test.task.repository.user.UserRepository;
import com.test.task.request.auth.AuthRequest;
import com.test.task.response.auth.AuthResponse;
import com.test.task.service.user.UserService;
import com.test.task.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private JwtProvider jwtProvider;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public AuthResponse authUser(AuthRequest request) {
        User user = userService.getUserByLogin(request.getLogin());

        byte[] passwordBytes = Base64.getDecoder().decode(request.getPassword());

        if(!passwordEncoder.matches(new String(passwordBytes), user.getPassword()))
            throw new ServiceException("Invalid password", HttpStatus.BAD_REQUEST);

        return new AuthResponse(jwtProvider.generateToken(user.getLogin()), user);
    }
}
