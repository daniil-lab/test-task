package com.test.task.user;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class GetUsersTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void test() {
        for (int i = 0; i < 3; i++) {
            CreateUserRequest request = new CreateUserRequest();
            request.setLogin("test" + i);
            request.setPassword(new String(Base64.getEncoder().encode("test".getBytes())));
            request.setRole(UserRole.USER);

            userService.createUser(request);
        }


        assertEquals(userService.getAllUsers().size(), 3);
    }
}
