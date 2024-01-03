package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id",nullable = false)
   private Long productId;

   @Column(name = "name",nullable = false)
   private String productName;

   @Column(name = "quantity",nullable = false)
   private int productQuantity;

   @Column(name = "category",nullable = false)
   private String productCategory;

   @Column(name = "price",nullable = false)
   private float productPrice;
}