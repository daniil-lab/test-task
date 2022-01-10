package com.test.task.service.article;

import com.test.task.entity.article.Article;
import com.test.task.entity.user.User;
import com.test.task.repository.article.ArticleRepository;
import com.test.task.request.article.CreateArticleRequest;
import com.test.task.request.article.UpdateArticleRequest;
import com.test.task.service.user.UserService;
import com.test.task.util.ArticleDateConverter;
import com.test.task.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    public List<Article> getArticleByPage(int pageSize, int page) {
        Pageable pageRequest = PageRequest.of(page, pageSize);

        Iterable<Article> pagedArticles = articleRepository.findAll(pageRequest);
        List<Article> articles = new ArrayList<>();

        pagedArticles.forEach(articles::add);

        return articles;
    }

    public Article createArticle(CreateArticleRequest request) {
        User user = userService.getUserById(request.getAuthorId());

        Instant date = ArticleDateConverter.getInstantByString(request.getDate());

        Article article = new Article(
                request.getTitle(),
                user,
                date,
                request.getContent()
        );

        articleRepository.save(article);

        return article;
    }

    public Article getArticleById(UUID id) {
        Optional<Article> article = articleRepository.findById(id);

        if(article.isEmpty())
            throw new ServiceException("Article not found", HttpStatus.NOT_FOUND);

        return article.get();
    }

    public List<Article> getAllArticles() {
        Iterable<Article> foundArticles = articleRepository.findAll();
        List<Article> articles = new ArrayList<>();

        foundArticles.forEach(articles::add);

        return articles;
    }

    @Transactional
    public Article removeArticle(UUID id) {
        Article article = this.getArticleById(id);

        articleRepository.delete(article);

        return article;
    }

    public Article updateArticle(UpdateArticleRequest request, UUID id) {
        Article article = this.getArticleById(id);

        if(request.getAuthorId() != null && !request.getAuthorId().equals(article.getAuthor().getId()))
            article.setAuthor(userService.getUserById(request.getAuthorId()));

        if(request.getContent() != null && !request.getContent().equals(article.getContent()))
            article.setContent(request.getContent());

        if(request.getDate() != null) {
            Instant date = ArticleDateConverter.getInstantByString(request.getDate());

            article.setPublishDate(date);
        }

        if(request.getTitle() != null && !request.getTitle().equals(article.getTitle()))
            article.setTitle(request.getTitle());

        articleRepository.save(article);

        return article;
    }
}
