package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleServiceInterface;
import ru.kata.spring.boot_security.demo.service.user.UserServiceInterface;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceInterface userService;
    private final RoleServiceInterface roleService;

    @Autowired
    public AdminController(UserServiceInterface userService, RoleServiceInterface roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userList(Model model, Principal principal) {
        model.addAttribute("getUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("principal", userService.findByUserName(principal.getName()).get());
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allRoles", roleService.allRoles());
        return "index";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        model.addAttribute("getUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user";
    }

    @PostMapping("/addNewUser")
    public String createUser(@ModelAttribute("user") User user) {
        if (userService.checkLogin(user)) {
            userService.saveUser(user);
            return "redirect:/admin";
        } else {
            return "/Error";
        }
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
