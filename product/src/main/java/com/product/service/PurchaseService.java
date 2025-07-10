package com.product.service;

import com.product.model.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase buyProduct(Long productId, String buyerUsername, int quantity);
    List<Purchase> getPurchasesByUser(String username);
}
