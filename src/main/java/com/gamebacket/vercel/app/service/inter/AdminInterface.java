package com.gamebacket.vercel.app.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface AdminInterface {
    void publishNewGame(String gameTitle, String gamePlatforms, float actualPrice, float discountPrice, int gamePlaytime, int gameAge, String genre, String website, String tags, String aboutGame, LocalDate releaseDate, int gameQuantity, String requirements, MultipartFile imagePath);

    void publishAccessories(String productName, String category, String description, float price, int quantity, MultipartFile imagePath);

    void confirmCompleteOrders(String orderNo);

}
