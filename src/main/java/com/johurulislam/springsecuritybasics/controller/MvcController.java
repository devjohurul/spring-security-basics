package com.johurulislam.springsecuritybasics.controller;

import com.johurulislam.springsecuritybasics.model.User;
import com.johurulislam.springsecuritybasics.service.UserService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MvcController {
    private final UserService userService;

    public MvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/login"})
    public String login(@RequestParam(required = false) String error, Model model) {
        if (error != null && error.equals("true")) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
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

    @PostMapping("/register-user")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "User already exists with username: " + user.getUsername());
            return "redirect:/register-user";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "Registration successful");

        return "redirect:/register-user";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(User user, RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "Admin already exists with username: " + user.getUsername());
            return "redirect:/register-admin";
        }
        userService.saveAdmin(user);
        redirectAttributes.addFlashAttribute("success", "Admin registration successful");
        return "redirect:/register-admin";
    }

}
