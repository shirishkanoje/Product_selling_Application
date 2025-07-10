package com.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // e.g., ROLE_ADMIN or ROLE_CUSTOMER

    // for checkout purpose
    private String name;
    private String contactNumber;
    private String address;
}
