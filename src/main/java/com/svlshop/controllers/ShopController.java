package com.svlshop.controllers;

import com.svlshop.security.entity.User;
import com.svlshop.security.service.UserService;
import com.svlshop.service.SessionObjectHolder;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping()
public class ShopController {

    private final SessionObjectHolder sessionObjectHolder;
    private final UserService userService;

    public ShopController(SessionObjectHolder sessionObjectHolder, UserService userService) {
        this.sessionObjectHolder = sessionObjectHolder;
        this.userService = userService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index() {
        sessionObjectHolder.addClick(); //считает количество вызовов метода из браузера
        return "index";
    }

    @RequestMapping({"/other"})
    public String other(Model model, HttpSession httpSession) {
        //считаем клики по ссылке "/products"
        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());

        // Создаем аналог кукис, который отрабатывает только в рамках сессии, используя HttpSession
        // HttpSession создается и внедряется Spring, но для страховки используем генератор, во избежание null:
        if (httpSession.getAttribute("myID") == null)
            httpSession.setAttribute("myID", UUID.randomUUID().toString());
        model.addAttribute("uuid", httpSession.getAttribute("myID"));

        return "other";
    }

    @GetMapping("/error")
    public String loginPage() {
        return "errorPage";
    }

    @GetMapping("/let-s-take-error")
    public String error() {
        throw new RuntimeException("Error for test!");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping("/temp/for-admins-and-manager")
    public String showUsers1(Principal principal, Model model) {
        User user = userService.findByPhoneNumber(principal.getName());
        String s = user.getName() + " " + user.getPhoneNumber();
        model.addAttribute("userInfo", s);
        return "temp/for-admins-and-manager";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/temp/for-auth")
    public String showUsers2(Principal principal, Model model) {
        User user = userService.findByPhoneNumber(principal.getName());
        String s = user.getName() + " " + user.getPhoneNumber();
        model.addAttribute("userInfo", s);
        return "temp/for-auth";
    }

    @PreAuthorize("isAuthenticated() and '+1-125-265-25-25' == authentication.principal.username")
    @GetMapping("/temp/for-one-user")
//    @ResponseBody
    public String showUsers3(Principal principal, Model model) {
        User user = userService.findByPhoneNumber(principal.getName());
        String s = user.getName() + " " + user.getPhoneNumber();
        model.addAttribute("userInfo", s);
        return "temp/for-one-user";
    }

    @PostAuthorize("isAuthenticated() and #phoneNumber == authentication.principal.username")
    @GetMapping("/temp/for-owner/{phoneNumber}")
    public String showUsers4(@PathVariable("phoneNumber") String phoneNumber, Principal principal, Model model) {

        User user = userService.findByPhoneNumber(principal.getName());
        if (user == null)
            throw new UsernameNotFoundException("The user with the number " + principal.getName() + " was not found");
        String s = user.getName() + " " + user.getPhoneNumber();
        model.addAttribute("userInfo", s);
        System.out.println("temp/for-owner/" + user.getPhoneNumber().substring(1));
        return "temp/for-owner/" + user.getPhoneNumber().substring(1);
    }
}