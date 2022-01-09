package com.test.task.article;

import com.test.task.entity.article.Article;
import com.test.task.entity.user.User;
import com.test.task.entity.user.UserRole;
import com.test.task.request.article.CreateArticleRequest;
import com.test.task.request.article.UpdateArticleRequest;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.service.article.ArticleService;
import com.test.task.service.user.UserService;
import com.test.task.util.ArticleDateConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UpdateArticleTest {
    @Autowired
    private ArticleService articleService;

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

        CreateArticleRequest createArticleRequest = new CreateArticleRequest();
        createArticleRequest.setContent("test");
        createArticleRequest.setTitle("test");
        createArticleRequest.setDate("2022-01-07 10:10:10");
        createArticleRequest.setAuthorId(user.getId());

        Article article = articleService.createArticle(createArticleRequest);

        CreateUserRequest createUpdateUserRequest = new CreateUserRequest();
        createUpdateUserRequest.setLogin("testUpdate");
        createUpdateUserRequest.setPassword(new String(Base64.getEncoder().encode("test".getBytes())));
        createUpdateUserRequest.setRole(UserRole.USER);

        User updateUser = userService.createUser(createUpdateUserRequest);

        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest();

        updateArticleRequest.setContent("testUpdate");
        updateArticleRequest.setTitle("testUpdate");
        updateArticleRequest.setDate("2022-01-06 10:10:10");
        updateArticleRequest.setAuthorId(updateUser.getId());

        articleService.updateArticle(updateArticleRequest, article.getId());

        Article updatedArticle = articleService.getArticleById(article.getId());

        assertEquals(updatedArticle.getAuthor().getId(), updateUser.getId());
        assertEquals(updatedArticle.getContent(), "testUpdate");
        assertEquals(updatedArticle.getTitle(), "testUpdate");
        assertEquals(ArticleDateConverter.getStringByInstant(updatedArticle.getPublishDate()), "2022-01-06 10:10:10");
    }
}
