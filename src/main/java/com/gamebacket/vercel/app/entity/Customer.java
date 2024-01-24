package com.gamebacket.vercel.app.entity;

import com.gamebacket.vercel.app.constants.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "id",nullable = false)
    private Long userId;

    @Column(name = "name",nullable = false)
    private String full_name;

    @Column(name = "email",nullable = false)
    private String userEmail;

    private String passwords;

    @Enumerated(EnumType.STRING)
    private UserRole roles;

    private LocalDate date_created;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Contact> contact;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Order> orders;

    @PrePersist
    public void onCreate(){
        date_created = LocalDate.now();
    }

}
