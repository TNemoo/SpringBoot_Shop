package com.svlshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ShopController {

    @RequestMapping({"", "/", "/index"})
    public String index() {
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
