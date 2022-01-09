package com.test.task.user;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import com.test.task.repository.user.UserRepository;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.lang.reflect.Executable;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class CreateUserTest {
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

        assertDoesNotThrow(() -> userService.getUserById(user.getId()).getId());

        User createdUser = userService.getUserById(user.getId());

        assertEquals(createdUser.getLogin(), "test");
        assertEquals(createdUser.getRole(), UserRole.USER);
    }
}
