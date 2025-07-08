package com.product.controller;

import com.product.model.AuthRequest;
import com.product.model.User;
import com.product.service.UserService;
import com.product.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        System.out.println("userDetails: " + userDetails);
        return "Login Successful!\nToken: " + jwtUtil.generateToken(userDetails.getUsername()) +
                "\nUsername: " + userDetails.getUsername();

    }
}
