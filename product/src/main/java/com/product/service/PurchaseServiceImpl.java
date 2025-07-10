package com.product.service;

import com.product.model.Product;
import com.product.model.Purchase;
import com.product.repository.ProductRepository;
import com.product.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Purchase buyProduct(Long productId, String buyerUsername, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setBuyerUsername(buyerUsername);
        purchase.setQuantity(quantity);
        purchase.setStatus("COMPLETED");

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getPurchasesByUser(String username) {
        return purchaseRepository.findByBuyerUsername(username);
    }
}
