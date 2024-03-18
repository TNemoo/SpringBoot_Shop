package com.svlshop.controllers;

import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.entity.User;
import com.svlshop.security.service.UserService;
import com.svlshop.service.BucketService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal, @RequestParam(required = false) String message) {
        if (principal == null)
            throw new RuntimeException("Please, log in");

        User user = userService.findByPhoneNumber(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
        model.addAttribute("person", userDTO);
        if (message != null && !message.isEmpty())
            model.addAttribute("message", message);
        return "users/profile";
    }

    //в userDTO все данные кроме password могут быть изменены, включая номер телефона, который в приложении уникальный
    //на пользователя указывает текущий principal
    @PostMapping("/profile")
    public String updateUserProfile(UserDTO userDTO, Principal principal) {
        if (principal == null)
            throw new RuntimeException("Please, log in");

        User user = userService.findByPhoneNumber(userDTO.getPhoneNumber());

        if (userDTO.getPassword() == null
                || userDTO.getPassword().isEmpty()
                || user == null
                || !principal.getName().equals(userDTO.getPhoneNumber())
                || !passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new RuntimeException("Password is not correct");

        String s = userService.save(userDTO, user.getPhoneNumber());

        return "redirect:/users/profile?message=" + s;
        //в метод передаем текущий номер телефона от принципала, что б вытащить данные пользователя из БД
    }

    @PostMapping("/profile/delete")
    public String deleteUserProfile(UserDTO userDTO, Principal principal) {
        if (principal == null)
            throw new RuntimeException("Please, log in");

        User user = userService.findByPhoneNumber(userDTO.getPhoneNumber());

        if (user == null)
            throw new UsernameNotFoundException("The user is not exist");

        if (userDTO.getPassword() == null
                || user == null
                || userDTO.getPassword().isEmpty()
                || !principal.getName().equals(userDTO.getPhoneNumber())
                || !passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new RuntimeException("Password is not correct");

        userService.delete(user);
        String s = "The user " + user.getName() + " has been deleted";

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //выбрасывает пользователя аутентификации после удаления профиля.

        return "redirect:/index";
    }
}
