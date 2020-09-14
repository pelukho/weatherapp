package com.example.weather.controller;

import com.example.weather.entity.Role;
import com.example.weather.entity.User;
import com.example.weather.service.RestService;
import com.example.weather.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class UserController {

    final RestService restService;
    final UserService userService;
    final PasswordEncoder passwordEncoder;

    public UserController(RestService restService, UserService userService, PasswordEncoder passwordEncoder) {
        this.restService = restService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User u) {

        User userFromDb = userService.findByUsername(u.getUsername());

        if (null != userFromDb) {
            return "login";
        }

        u.setRoles(Collections.singleton(Role.USER));
        u.setActive(true);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setUsername(u.getUsername());

        userService.saveUser(u);

        return "login";
    }
}
