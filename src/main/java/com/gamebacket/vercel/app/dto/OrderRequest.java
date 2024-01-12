package com.gamebacket.vercel.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequest {
//    for product details
    private String productName;
    private int productQuantity;
}
