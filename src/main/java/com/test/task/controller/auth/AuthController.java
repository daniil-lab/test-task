package com.test.task.controller.auth;

import com.test.task.request.auth.AuthRequest;
import com.test.task.response.auth.AuthResponse;
import com.test.task.service.auth.AuthService;
import com.test.task.util.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "Auth API")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/")
    @Operation(summary = "Auth user")
    public ResponseEntity<ServiceResponse<AuthResponse>> authUser(
            @Valid
            @RequestBody
                AuthRequest request
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                authService.authUser(request),
                "Auth successful"
        ), HttpStatus.OK);
    }
}
