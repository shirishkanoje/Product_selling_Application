package com.product.service;

import com.product.model.User;
import com.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully";
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @Override
    public String updateUser(User user) {
        // optional: check if user exists first
        User existingUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found for update: " + user.getUsername()));

        existingUser.setName(user.getName());
        existingUser.setContactNumber(user.getContactNumber());
        existingUser.setAddress(user.getAddress());

        userRepo.save(existingUser);
        return "User details updated successfully";
    }

    @Override
    public List<User> getAllCustomersWhoCheckedOut() {
        return userRepo.findAll()
                .stream()
                .filter(user -> user.getRole().equals("ROLE_CUSTOMER") &&
                        user.getName() != null &&
                        user.getContactNumber() != null &&
                        user.getAddress() != null)
                .collect(Collectors.toList());
    }
}
