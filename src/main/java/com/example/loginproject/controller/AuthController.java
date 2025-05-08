package com.example.loginproject.controller;

import com.example.loginproject.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authService.authenticate(username, password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard.html";
        }
        return "redirect:/index.html?error=true";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        authService.register(username, password);
        return "redirect:/index.html?registered=true";
    }
}
