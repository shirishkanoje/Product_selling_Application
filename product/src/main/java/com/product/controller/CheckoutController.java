package com.product.controller;

import com.product.model.User;
import com.product.service.UserService;
import com.product.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> submitCheckout(
            @Valid @RequestBody CheckoutRequest request,
            Principal principal) {

        String username = principal.getName();

        // Save updated user details
        User user = userService.getByUsername(username);
        user.setName(request.getName());
        user.setContactNumber(request.getContactNumber());
        user.setAddress(request.getAddress());
        userService.updateUser(user); // make sure this method exists

        // Mark purchases as completed or move to "orders" (optional)

        return ResponseEntity.ok("Checkout completed successfully ✅");
    }

    @Data
    public static class CheckoutRequest {
        private String name;
        private String contactNumber;
        private String address;
    }
}
