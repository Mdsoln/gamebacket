package com.gamebacket.vercel.app.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccessoryRequest {
    private String productName;
    private String category;
    private float price;
    private String description;
    private int quantity;

    @Column(name = "image",nullable = false,columnDefinition = "BLOB")
    private byte[] image;
}
