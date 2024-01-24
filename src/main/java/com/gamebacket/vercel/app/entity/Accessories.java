package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "accessory")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Accessories {
    @Id
    @SequenceGenerator(
            name = "accessories_sequence",
            sequenceName = "accessories_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accessories_sequence"
    )
    @Column(name = "id",nullable = false)
    private Long accessoryId;

    @Column(name = "name",nullable = false)
    private String productName;

    private String category;
    private float price;
    private String description;
    private int quantity;
    private String image;

    @OneToMany(mappedBy = "accessory",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderItem> orderItem;
}
