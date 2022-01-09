package com.test.task.repository.article;

import com.test.task.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM article WHERE publish_date BETWEEN ?1 AND ?2")
    List<Article> getArticlesByDates(Timestamp startDate, Timestamp endDate);
}
