package com.svlshop.controllers;

import com.svlshop.dto.BucketDTO;
import com.svlshop.security.repository.UserRepository;
import com.svlshop.service.BucketService;
import com.svlshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    private final ProductService productService;
    private final BucketService bucketService;
    private final UserRepository userRepository;

    public BucketController(ProductService productService, BucketService bucketService, UserRepository userRepository) {
        this.productService = productService;
        this.bucketService = bucketService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showBucket(Model model, Principal principal) {
        BucketDTO bucketDTO = bucketService.getBucketDTO(principal.getName());
        model.addAttribute("bucketDTO", bucketDTO.getBucketDetailsDTOS());
        model.addAttribute("sum", bucketDTO.getTotal());
        model.addAttribute("quantity", bucketDTO.getQuantity());
        return "products/bucket";
    }

    @GetMapping("/{productId}")
    public String addProductToBucket(@PathVariable Long productId, Principal principal) {
        bucketService.addProductToUserBucket(productId, principal.getName());
        return "redirect:/bucket";
    }

    @GetMapping("/remove/{productId}")
    public String temp(@PathVariable Long productId, Principal principal) {
        bucketService.removeProductFromUserBucket(productId, principal.getName());
        return "redirect:/bucket";
    }
}
