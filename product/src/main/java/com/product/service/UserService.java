package com.product.service;

import com.product.model.User;

import java.util.List;

public interface UserService {

    // Register a new user (Admin or Customer)
    String register(User user);

    // Fetch user by username
    User getByUsername(String username);

    // Update user's name, contact number, or address (used during checkout)
    String updateUser(User user);

    List<User> getAllCustomersWhoCheckedOut();

}
