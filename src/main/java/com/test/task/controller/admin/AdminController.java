package com.test.task.controller.admin;

import com.test.task.dto.article.ArticleDTO;
import com.test.task.service.admin.AdminService;
import com.test.task.util.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Admin API")
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "Bearer")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Get article counts by week")
    @GetMapping("/week")
    public ResponseEntity<ServiceResponse<List<Integer>>> getAllArticles() {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                adminService.getArticlesByWeek(),
                "Article counts returned"
        ), HttpStatus.OK);
    }
}
