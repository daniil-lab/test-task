package com.test.task.dto.article;

import com.test.task.dto.user.UserDTO;
import com.test.task.entity.article.Article;
import com.test.task.util.ArticleDateConverter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;

public class ArticleDTO {
    private UUID id;

    private String title;

    private String content;

    private String publishDate;

    private UserDTO author;

    public ArticleDTO() {}

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.publishDate = ArticleDateConverter.getStringByInstant(article.getPublishDate());
        this.author = new UserDTO(article.getAuthor());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
