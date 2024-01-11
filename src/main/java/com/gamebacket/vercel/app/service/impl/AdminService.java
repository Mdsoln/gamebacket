package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.entity.Accessories;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.exc.EmptyOrNullValueStorageException;
import com.gamebacket.vercel.app.repo.AccessoriesRepo;
import com.gamebacket.vercel.app.repo.GameRepo;
import com.gamebacket.vercel.app.service.inter.AdminInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Objects;

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
        try {
            String dir = storeImagePathToDatabase(imagePath);
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
                    .gameAge(gameAge)
                    .genre(genre)
                    .about(aboutGame)
                    .releaseDate(releaseDate)
                    .requirements(requirements)
                    .image(dir)
                    .build();
            gameRepo.save(games);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publishAccessories(String productName, String category, String description,
                                   float price, int quantity, MultipartFile imagePath) {
          try {
              String dir = storeImagePathToDatabase(imagePath);
              Accessories accessories = Accessories
                      .builder()
                      .productName(productName)
                      .category(category)
                      .description(description)
                      .price(price)
                      .quantity(quantity)
                      .image(dir)
                      .build();
              accessoriesRepo.save(accessories);

          } catch (IOException e) {
              throw new RuntimeException(e);
          }
    }

    private String storeImagePathToDatabase(MultipartFile image) throws IOException {
        String uploadDirectory = "C:/Users/rojo/Documents/SolnCodes/gamebacket/images";
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));


        if (imageName.contains("..")){
            throw new EmptyOrNullValueStorageException("Invalid file format");
        }
        Path pathUpload = Paths.get(uploadDirectory);
        if (!Files.exists(pathUpload)){
            Files.createDirectories(pathUpload);
        }

        Path uploads = pathUpload.resolve(imageName);
        Files.copy(image.getInputStream(),uploads, StandardCopyOption.REPLACE_EXISTING);

        return uploads.toString();
    }
}
