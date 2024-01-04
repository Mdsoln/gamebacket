package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.dto.AccessoryRequest;
import com.gamebacket.vercel.app.dto.GamePublisher;
import com.gamebacket.vercel.app.entity.Accessories;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.repo.AccessoriesRepo;
import com.gamebacket.vercel.app.repo.GameRepo;
import com.gamebacket.vercel.app.service.inter.AdminInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;


    @Override
    public void publishNewGame(GamePublisher gamePublisher) {
        Games game = Games
                .builder()
                .gameTitle(gamePublisher.getGameTitle())
                .gamePlatforms(gamePublisher.getGamePlatforms())
                .ActualPrice(gamePublisher.getActualPrice())
                .DiscountPrice(gamePublisher.getDiscountPrice())
                .gamePlaytime(gamePublisher.getGamePlaytime())
                .gameAge(gamePublisher.getGameAge())
                .genre(gamePublisher.getGenre())
                .website(gamePublisher.getWebsite())
                .tags(gamePublisher.getTags())
                .about(gamePublisher.getAboutGame())
                .requirements(gamePublisher.getRequirements())
                .releaseDate(gamePublisher.getReleaseDate())
                .gameQuantity(gamePublisher.getGameQuantity())
                .gameCategory(gamePublisher.getGameCategory())
                .image(gamePublisher.getImage())
                .build();

        gameRepo.save(game);
    }

    @Override
    public void publishAccessories(AccessoryRequest accessoryRequest) {
        Accessories accessories = Accessories
                .builder()
                .productName(accessoryRequest.getProductName())
                .category(accessoryRequest.getCategory())
                .price(accessoryRequest.getPrice())
                .description(accessoryRequest.getDescription())
                .quantity(accessoryRequest.getQuantity())
                .image(accessoryRequest.getImage())
                .build();
        accessoriesRepo.save(accessories);
    }


}
