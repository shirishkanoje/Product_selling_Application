package com.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "✨ Welcome to the Product API! " +
                "Use /api/products to access product endpoints.";
    }
}
