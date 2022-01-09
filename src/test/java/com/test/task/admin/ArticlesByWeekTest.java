package com.test.task.admin;

import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import com.test.task.request.article.CreateArticleRequest;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.service.admin.AdminService;
import com.test.task.service.article.ArticleService;
import com.test.task.service.user.UserService;
import com.test.task.util.ArticleDateConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ArticlesByWeekTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void test() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setLogin("test");
        createUserRequest.setPassword(new String(Base64.getEncoder().encode("test".getBytes())));
        createUserRequest.setRole(UserRole.USER);

        User user = userService.createUser(createUserRequest);

        for (int i = 0; i < 7; i++) {
            CreateArticleRequest createArticleRequest = new CreateArticleRequest();
            createArticleRequest.setContent("test");
            createArticleRequest.setTitle("test");
            createArticleRequest.setDate(ArticleDateConverter.getStringByInstant(Instant.now().minus(i, ChronoUnit.DAYS)));
            createArticleRequest.setAuthorId(user.getId());

            articleService.createArticle(createArticleRequest);
        }

        List<Integer> counts = adminService.getArticlesByWeek();

        counts.forEach((val) -> assertEquals(val, 1));
    }
}
