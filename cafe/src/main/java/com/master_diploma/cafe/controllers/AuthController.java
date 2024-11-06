package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.dto.UserRegistrationDto;
import com.master_diploma.cafe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }
    @PostMapping("/registered")
    public String registerUser(@ModelAttribute UserRegistrationDto registrationDto, Model model) {
        if (userService.userExists(registrationDto.getEmail())) {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            return "register";
        }
        userService.registerNewUser(registrationDto);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
