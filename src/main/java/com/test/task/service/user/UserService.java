package com.test.task.service.user;

import com.test.task.entity.user.User;
import com.test.task.repository.user.UserRepository;
import com.test.task.request.user.CreateUserRequest;
import com.test.task.request.user.UpdateUserRequest;
import com.test.task.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    // Cycle fix
//    public UserService(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }

    public List<User> getAllUsers() {
        Iterable<User> foundUsers = userRepository.findAll();
        List<User> users = new ArrayList<>();

        foundUsers.forEach(users::add);

        return users;
    }

    public User getUserById(UUID id) {
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty())
            throw new ServiceException("User not found", HttpStatus.NOT_FOUND);

        return foundUser.get();
    }

    public User getUserByLogin(String login) {
        Optional<User> foundUser = userRepository.findByLogin(login);

        if(foundUser.isEmpty())
            throw new ServiceException("User not found", HttpStatus.NOT_FOUND);

        return foundUser.get();
    }

    @Transactional
    public User removeUser(UUID id) {
        User user = this.getUserById(id);

        userRepository.delete(user);

        return user;
    }

    public User createUser(CreateUserRequest request) {
        byte[] passwordBytes = Base64.getDecoder().decode(request.getPassword());

        User user = new User(request.getLogin(), passwordEncoder.encode(new String(passwordBytes)), request.getRole());

        Optional<User> duplicateCheck = userRepository.findByLogin(request.getLogin());

        if(duplicateCheck.isPresent())
            throw new ServiceException("User with given login already exist", HttpStatus.NOT_FOUND);

        userRepository.save(user);

        return user;
    }

    public User updateUser(UpdateUserRequest request, UUID id) {
        User user = this.getUserById(id);

        if(request.getLogin() != null && !request.getLogin().equals(user.getLogin()))
            user.setLogin(request.getLogin());

        if(request.getPassword() != null) {
            byte[] passwordBytes = Base64.getDecoder().decode(request.getPassword());

            user.setPassword(passwordEncoder.encode(new String(passwordBytes)));
        }

        if(request.getRole() != null && !request.getRole().equals(user.getRole()))
            user.setRole(request.getRole());

        userRepository.save(user);

        return user;
    }
}
