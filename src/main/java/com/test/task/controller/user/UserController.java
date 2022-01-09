package com.test.task.controller.user;

import com.test.task.dto.user.UserDTO;
import com.test.task.entity.user.User;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.request.user.UpdateUserRequest;
import com.test.task.service.user.UserService;
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
@Tag(name = "User API")
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "Bearer")
public class UserController {
    @Autowired
    private UserService userService;

//    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Create user")
    @PostMapping("/")
    public ResponseEntity<ServiceResponse<UserDTO>> createUser(
            @Valid
            @RequestBody
                CreateUserRequest request
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.CREATED.value(),
                new UserDTO(userService.createUser(request)),
                "User created"
        ), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Update user")
    @PatchMapping("/{userId}")
    public ResponseEntity<ServiceResponse<UserDTO>> updateUser(
            @Valid
            @RequestBody
                    UpdateUserRequest request,
            @PathVariable
                UUID userId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new UserDTO(userService.updateUser(request, userId)),
                "User updated"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get user by ID")
    @GetMapping("/{userId}")
    public ResponseEntity<ServiceResponse<UserDTO>> getUserById(
            @PathVariable
                    UUID userId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new UserDTO(userService.getUserById(userId)),
                "User returned"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity<ServiceResponse<List<UserDTO>>> getAllUsers() {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                userService.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList()),
                "Users returned"
        ), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Remove user")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ServiceResponse<UserDTO>> removeUser(
            @PathVariable
                    UUID userId
    ) {
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                new UserDTO(userService.removeUser(userId)),
                "User removed"
        ), HttpStatus.OK);
    }
}
