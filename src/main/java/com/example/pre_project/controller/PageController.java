package com.example.pre_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login-page")
    public String showSignUpForm() {
        return "login.html";
    }

    @GetMapping("/admin-page")
    public String showAdminPage() {
        return "index.html";
    }

    @GetMapping("/user-page")
    public String showUserPage() {
        return "user.html";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "login.html";
    }

}