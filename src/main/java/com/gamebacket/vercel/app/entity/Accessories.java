package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accessory")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Accessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long accessoryId;

    @Column(name = "name",nullable = false)
    private String productName;

    private String category;
    private float price;
    private String description;
    private int quantity;

    @Column(name = "image",nullable = false,columnDefinition = "BLOB")
    private byte[] image;
}
