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
    public String index(Model model, HttpSession httpSession) {
        sessionObjectHolder.addClick();
        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());

        if (httpSession.getAttribute("myId") == null) {
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("myId", uuid);
            System.out.println("Generated UUID: " + uuid);
        }

        return "index";
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

