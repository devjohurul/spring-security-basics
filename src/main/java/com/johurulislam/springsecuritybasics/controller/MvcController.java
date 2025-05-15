package com.johurulislam.springsecuritybasics.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MvcController {
    @GetMapping({"/","/login"})
    public String login(@RequestParam(required = false) String error, Model model) {
        if(error != null && error.equals("true")){
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_ADMIN")){
            return "admin-dashboard";
        }
        return "user-dashboard";
    }
    @GetMapping({"/register-user"})
    public String registerUser() {
        return "register-user";
    }
    @GetMapping({"register-admin"})
    public String registerAdmin() {
        return "register-admin";
    }
    @GetMapping("/user-dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}
