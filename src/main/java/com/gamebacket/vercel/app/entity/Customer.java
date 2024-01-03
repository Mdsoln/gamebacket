package com.gamebacket.vercel.app.entity;

import com.gamebacket.vercel.app.constants.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long userId;

    @Column(name = "name",nullable = false)
    private String full_name;

    @Column(name = "email",nullable = false)
    private String user_email;

    private String passwords;

    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Contact> contact;
}
