package com.svlshop.controllers;

import com.svlshop.service.BucketService;
import com.svlshop.service.ProductService;
import com.svlshop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final BucketService bucketService;
    private final SessionObjectHolder sessionObjectHolder;

    public ProductController(ProductService productService, BucketService bucketService
            , SessionObjectHolder sessionObjectHolder) {

        this.productService = productService;
        this.bucketService = bucketService;
        this.sessionObjectHolder = sessionObjectHolder;
    }


    @GetMapping("/products-all")
    public String getAll(Model model) {
        model.addAttribute("productsAll", productService.findAll());
        return "/products/products-all";
    }

    @GetMapping("/{productId}")
    public String addProductToBucket(@PathVariable Long productId, Principal principal) {
        bucketService.addProductToUserBucket(productId, principal.getName());
        sessionObjectHolder.addClick();
        return "redirect:/products/products-all";
    }


}
