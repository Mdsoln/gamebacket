package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.constants.Status;
import com.gamebacket.vercel.app.dto.SalesReport;
import com.gamebacket.vercel.app.entity.Accessories;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.entity.Order;
import com.gamebacket.vercel.app.entity.OrderStatus;
import com.gamebacket.vercel.app.exc.EmptyOrNullValueStorageException;
import com.gamebacket.vercel.app.exc.SearchExceptions;
import com.gamebacket.vercel.app.repo.*;
import com.gamebacket.vercel.app.service.inter.AdminInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;
    private final OrderRepo orderRepo;
    private final OrderStatusRepo orderStatusRepo;
    private final OrderItemRepo orderItemRepo;

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

    @Override
    public void confirmCompleteOrders(String orderNo) {
        Order order = orderRepo.findByOrderNo(orderNo);
        if (order != null){
            OrderStatus completeStatus = order.getOrderStatus();
            completeStatus.setOrder_status(Status.STATUS_COMPLETED);
            completeStatus.setDate_updated(LocalDate.now());
            orderStatusRepo.save(completeStatus);

            order.setDate_updated(LocalDate.now());
            orderRepo.save(order);
        }
    }

    //filtering sales and display sales based on current day
    @Override
    public List<SalesReport> findTodaysSales() {
        List<SalesReport> dailyReport = orderItemRepo.findTotalSalesPerDay();
        LocalDate today = LocalDate.now();

        return dailyReport.stream()
                .filter(report-> report.getDateCreated().equals(today))
                .collect(Collectors.toList());
    }


     @Scheduled(fixedRate = 1000 * 60 * 60, initialDelay = 1000) // Schedule every hour with a 1-second initial delay
    public void updateTodaysSales() {
    List<SalesReport> todaysSalesReport = findTodaysSales();
    // Update your UI logic here using todaysSalesReport data
         //using react or angular framework to update the ui
         //sometimes websocket etc.
    }

    private String storeImagePathToDatabase(MultipartFile image) throws IOException {
        String uploadDirectory = "/dir/uploads";
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
