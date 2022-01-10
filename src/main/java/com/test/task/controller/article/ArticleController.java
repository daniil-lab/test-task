package com.test.task.controller.article;

import com.test.task.dto.article.ArticleDTO;
import com.test.task.dto.user.UserDTO;
import com.test.task.request.article.CreateArticleRequest;
import com.test.task.request.article.UpdateArticleRequest;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.request.user.UpdateUserRequest;
import com.test.task.service.article.ArticleService;
import com.test.task.util.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Article API")
@RequestMapping("/api/v1/article")
@SecurityRequirement(name = "Bearer")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Create article")
    @PostMapping("/")
    public ResponseEntity<ServiceResponse<ArticleDTO>> createArticle(
            @Valid
            @RequestBody
                    CreateArticleRequest request
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.CREATED.value(),
                new ArticleDTO(articleService.createArticle(request)),
                "Article created"
        ), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Update article")
    @PatchMapping("/{articleId}")
    public ResponseEntity<ServiceResponse<ArticleDTO>> updateArticle(
            @Valid
            @RequestBody
                    UpdateArticleRequest request,
            @PathVariable
                    UUID articleId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new ArticleDTO(articleService.updateArticle(request, articleId)),
                "Article updated"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get article by ID")
    @GetMapping("/{userId}")
    public ResponseEntity<ServiceResponse<ArticleDTO>> getArticleById(
            @PathVariable
                    UUID articleId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new ArticleDTO(articleService.getArticleById(articleId)),
                "Article returned"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get all articles")
    @GetMapping("/")
    public ResponseEntity<ServiceResponse<List<ArticleDTO>>> getAllArticles() {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                articleService.getAllArticles().stream().map(ArticleDTO::new).collect(Collectors.toList()),
                "Articles returned"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get all articles")
    @GetMapping("/")
    public ResponseEntity<ServiceResponse<List<ArticleDTO>>> getArticlesByPage(
            @RequestParam
                int pageSize,
            @RequestParam
                int page
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                articleService.getArticleByPage(pageSize, page).stream().map(ArticleDTO::new).collect(Collectors.toList()),
                "Articles returned"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Remove article")
    @DeleteMapping("/{articleId}")
    public ResponseEntity<ServiceResponse<ArticleDTO>> removeArticle(
            @PathVariable
                    UUID articleId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new ArticleDTO(articleService.removeArticle(articleId)),
                "Article removed"
        ), HttpStatus.OK);
    }
}
