package com.svlshop.controllers;

import com.svlshop.service.SessionObjectHolder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping()
public class ShopController {

    private final SessionObjectHolder sessionObjectHolder;

    public ShopController(SessionObjectHolder sessionObjectHolder) {
        this.sessionObjectHolder = sessionObjectHolder;
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

}

