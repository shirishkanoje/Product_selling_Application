package com.product.controller;

import com.product.model.AuthRequest;
import com.product.model.User;


import com.product.service.UserService;
import com.product.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authManager;



    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody User user) {
        user.setRole("ROLE_ADMIN");
        return userService.register(user);
    }

    @PostMapping("/register/customer")
    public String registerCustomer(@RequestBody User user) {
        user.setRole("ROLE_CUSTOMER");
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()
                )
        );

        User user = userService.getByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return "Login Successful!\nUsername: " + user.getUsername() +
                "\nRole: " + user.getRole() +
                "\nToken: " + token;
    }
}
