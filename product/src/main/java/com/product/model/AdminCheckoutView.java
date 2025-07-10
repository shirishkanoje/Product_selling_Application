package com.product.model;

import lombok.Data;
import java.util.List;

@Data
public class AdminCheckoutView {
    private String name;
    private String contactNumber;
    private String address;
    private List<Purchase> purchases;
    private double totalAmount;
}
