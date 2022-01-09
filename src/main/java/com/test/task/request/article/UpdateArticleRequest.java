package com.test.task.request.article;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class UpdateArticleRequest {
    @Length(min = 1, max = 100)
    private String title;

    @Length(min = 1)
    private String content;

    private UUID authorId;

    @Pattern(regexp = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})")
    private String date;

    public UpdateArticleRequest() {
    }

    public UpdateArticleRequest(String title, String content, UUID authorId, String date) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.date = date;
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

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
