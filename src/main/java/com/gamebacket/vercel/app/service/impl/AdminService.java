package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.entity.Accessories;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.repo.AccessoriesRepo;
import com.gamebacket.vercel.app.repo.GameRepo;
import com.gamebacket.vercel.app.service.inter.AdminInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;

    @Override
    public void publishNewGame(String gameTitle, String gamePlatforms, float actualPrice,
                               float discountPrice, int gamePlaytime, int gameAge, String genre,
                               String website, String tags, String aboutGame, LocalDate releaseDate,
                               int gameQuantity, String requirements, MultipartFile imagePath) {
        Games games = Games
                .builder()
                .gameTitle(gameTitle)
                .gamePlatforms(gamePlatforms)
                .actualPrice(actualPrice)
                .discountPrice(discountPrice)
                .gamePlaytime(gamePlaytime)
                .gameQuantity(gameQuantity)
                .website(website)
                .tags(tags)
                .genre(genre)
                .about(aboutGame)
                .releaseDate(releaseDate)
                .requirements(requirements)
                .image(imagePath.getOriginalFilename())
                .build();
        gameRepo.save(games);
    }

    @Override
    public void publishAccessories(String productName, String category, String description,
                                   float price, int quantity, MultipartFile imagePath) {
        Accessories accessories = Accessories
                .builder()
                .productName(productName)
                .category(category)
                .description(description)
                .price(price)
                .quantity(quantity)
                .image(imagePath.getOriginalFilename())
                .build();
        accessoriesRepo.save(accessories);
    }
}
