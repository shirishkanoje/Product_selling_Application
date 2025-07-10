package com.product.controller;

import com.product.model.*;
import com.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/checkout-summary")
    public ResponseEntity<List<AdminCheckoutView>> getAllCheckoutSummaries() {
        List<User> users = userService.getAllCustomersWhoCheckedOut();
        List<AdminCheckoutView> summaries = new ArrayList<>();

        for (User user : users) {
            List<Purchase> purchases = purchaseService.getPurchasesByUser(user.getUsername());

            double total = purchases.stream()
                    .mapToDouble(p -> p.getProduct().getPrice() * p.getQuantity())
                    .sum();

            AdminCheckoutView view = new AdminCheckoutView();
            view.setName(user.getName());
            view.setContactNumber(user.getContactNumber());
            view.setAddress(user.getAddress());
            view.setPurchases(purchases);
            view.setTotalAmount(total);

            summaries.add(view);
        }

        return ResponseEntity.ok(summaries);
    }
}
