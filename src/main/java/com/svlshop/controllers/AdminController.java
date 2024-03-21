package com.svlshop.controllers;

import com.svlshop.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasAuthority('ADMIN')") //можно так, вместо строки в filterChain + @EnableMethodSecurity
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("people", userService.findAll());
        return "admins/users";
    }



}
