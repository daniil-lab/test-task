package com.test.task.service.admin;

import com.test.task.entity.article.Article;
import com.test.task.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AdminService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Integer> getArticlesByWeek() {
        List<Integer> counts = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            counts.add(articleRepository.getArticlesByDates(Timestamp.from(Instant
                            .now()
                            .truncatedTo(ChronoUnit.DAYS)
                            .truncatedTo(ChronoUnit.HOURS)
                            .truncatedTo(ChronoUnit.MINUTES)
                            .truncatedTo(ChronoUnit.SECONDS)
                            .minus(i, ChronoUnit.DAYS)),
                    Timestamp.from(Instant
                            .now()
                            .truncatedTo(ChronoUnit.DAYS)
                            .plus(23, ChronoUnit.HOURS)
                            .plus(59, ChronoUnit.MINUTES)
                            .plus(59, ChronoUnit.SECONDS)
                            .minus(i, ChronoUnit.DAYS))).size());

        return counts;
    }
}
