package com.svlshop.controllers;

import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/index";
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("person") UserDTO userDTO) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO))
            return "redirect:/index";

        model.addAttribute("user", userDTO);
        return "auth/registration";
    }
}
