package com.test.task.entity.article;

import com.test.task.entity.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Article {
    @Id
    private UUID id = UUID.randomUUID();

    @Length(max = 100)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant publishDate;

    private String content;

    public Article() {}

    public Article(String title, User author, Instant publishDate, String content) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
