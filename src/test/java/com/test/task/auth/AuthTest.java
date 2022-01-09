package com.test.task.auth;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import com.test.task.request.auth.AuthRequest;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.response.auth.AuthResponse;
import com.test.task.service.auth.AuthService;
import com.test.task.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class AuthTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void test() {
        CreateUserRequest request = new CreateUserRequest();
        request.setLogin("test");
        request.setPassword(new String(Base64.getEncoder().encode("test".getBytes())));
        request.setRole(UserRole.USER);

        User user = userService.createUser(request);

        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin("test");
        authRequest.setPassword(new String(Base64.getEncoder().encode("test".getBytes())));

        assertDoesNotThrow(() ->
            authService.authUser(authRequest)
        );
    }
}
