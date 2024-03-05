package com.gamebacket.vercel.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopSellingProductDTO {
    private String productName;
    private float price;
    private String image;
    private int orderCount;
}
