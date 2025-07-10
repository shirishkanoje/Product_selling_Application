package com.product.controller;

import com.product.model.*;
import com.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Public
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Public
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    // Customer only - Checkout view
    @GetMapping("/checkout")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CheckoutResponse> checkout(Principal principal) {
        String username = principal.getName();

        User user = userService.getByUsername(username);
        List<Purchase> purchases = purchaseService.getPurchasesByUser(username);

        double total = purchases.stream()
                .mapToDouble(p -> p.getProduct().getPrice() * p.getQuantity())
                .sum();

        CheckoutResponse response = new CheckoutResponse();
        response.setName(user.getName());
        response.setContactNumber(user.getContactNumber());
        response.setAddress(user.getAddress());
        response.setPurchases(purchases);
        response.setTotalAmount(total);

        return ResponseEntity.ok(response);
    }
}
