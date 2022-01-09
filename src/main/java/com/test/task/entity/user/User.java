package com.test.task.entity.user;

import com.test.task.entity.article.Article;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "_user")
public class User {
    @Id
    private UUID id = UUID.randomUUID();

    private String login;

    private String password;

    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.LAZY)
    private List<Article> articles;

    public User() {}

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
