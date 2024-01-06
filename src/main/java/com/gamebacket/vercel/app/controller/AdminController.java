package com.gamebacket.vercel.app.controller;

import com.gamebacket.vercel.app.service.impl.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/publishGame")
    public ResponseEntity<String> publishGameToServer(
            @RequestParam(name = "gameTitle") @Valid String gameTitle,
            @RequestParam(name = "gamePlatforms",required = false)@Valid String gamePlatforms,
            @RequestParam(name = "actualPrice") @Valid float actualPrice,
            @RequestParam(name = "discountPrice")@Valid float discountPrice,
            @RequestParam(name = "gamePlaytime",required = false) @Valid int gamePlaytime,
            @RequestParam(name = "gameAge",required = false)@Valid int gameAge,
            @RequestParam(name = "genre") @Valid String genre,
            @RequestParam(name = "website")@Valid String website,
            @RequestParam(name = "tags")@Valid String tags,
            @RequestParam(name = "aboutGame",required = false) @Valid String aboutGame,
            @RequestParam(name = "releaseDate",required = false)@Valid LocalDate releaseDate,
            @RequestParam(name = "gameQuantity",required = false) @Valid int gameQuantity,
            @RequestParam(name = "requirements",required = false) @Valid String requirements,
            @RequestParam(name = "file") MultipartFile imagePath
    ){
        adminService.publishNewGame(gameTitle,gamePlatforms,actualPrice,
                discountPrice,gamePlaytime,gameAge,
                genre,website,tags,aboutGame,releaseDate,
                gameQuantity,requirements,imagePath);
        return ResponseEntity.ok("published successfully");
    }


    @PostMapping("/publishAccessory")
    public ResponseEntity<String> publishProduct(
            @RequestParam(name = "productName",required = false) String productName,
            @RequestParam(name = "category",required = false) String category,
            @RequestParam(name = "description",required = false) String description,
            @RequestParam(name = "price") float price,
            @RequestParam(name = "quantity",required = false) int quantity,
            @RequestParam(name = "file") MultipartFile imagePath
    ){
        adminService.publishAccessories(productName,category,description,price,quantity,imagePath);
        return ResponseEntity.ok("published successfully");
    }
}
