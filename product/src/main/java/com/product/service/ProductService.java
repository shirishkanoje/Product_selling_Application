package com.product.service;

import com.product.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product updatedProduct);
    String deleteProduct(Long id);
}
