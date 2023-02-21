package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.user.UserServiceInterface;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserRestController {
      private final UserServiceInterface userService;

    public UserRestController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(Principal principal) {
        Optional<User> user = userService.findByUserName(principal.getName());
        return new ResponseEntity<>(user.orElse(null), HttpStatus.OK);
    }
}
