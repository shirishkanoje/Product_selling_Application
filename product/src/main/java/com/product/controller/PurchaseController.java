package com.product.controller;

import com.product.model.Purchase;
import com.product.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @PostMapping("/{productId}/buy")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Purchase> buyProduct(
            @PathVariable Long productId,
            @RequestParam int quantity,
            Principal principal
    ) {
        String username = principal.getName(); // Get username from JWT
        Purchase purchase = purchaseService.buyProduct(productId, username, quantity);
        return ResponseEntity.ok(purchase);
    }

    /**
     * Get all purchases for the logged-in customer.
     */
    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Purchase>> getMyPurchases(Principal principal) {
        String username = principal.getName();
        List<Purchase> purchases = purchaseService.getPurchasesByUser(username);
        return ResponseEntity.ok(purchases);
    }
}
