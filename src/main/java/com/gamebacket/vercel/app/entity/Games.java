package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Games {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id",nullable = false)
   private Long gameId;

   @Column(name = "name",nullable = false)
   private String gameTitle;

   @Column(name = "platforms",nullable = false)
   private String gamePlatforms;

   @Column(name = "price",nullable = false)
   private float ActualPrice;

   @Column(name = "discount",nullable = false)
   private float DiscountPrice;

   @Column(name = "playtime",nullable = false)
   private int gamePlaytime;

   @Column(name = "age",nullable = false)
   private int gameAge;

   private String genre;
   private String website;
   private String tags;

   @Column(name = "description",nullable = false)
   private String about;

   private String requirements;

   @Column(name = "released",nullable = false)
   private String releaseDate;




   @Column(name = "quantity",nullable = false)
   private int productQuantity;

   @Column(name = "category",nullable = false)
   private String productCategory;

}