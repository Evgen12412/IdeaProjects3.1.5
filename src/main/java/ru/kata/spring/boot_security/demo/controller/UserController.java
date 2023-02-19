package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
     public String getUser(Model model) {
        model.addAttribute("getUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/user";
    }
}
