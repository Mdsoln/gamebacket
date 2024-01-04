package com.gamebacket.vercel.app.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GamePublisher {
    private String gameTitle;
    private String gamePlatforms;
    private float ActualPrice;
    private float DiscountPrice;
    private int gamePlaytime;
    private int gameAge;
    private String genre;
    private String website;
    private String tags;
    private String aboutGame;
    private String requirements;
    private String releaseDate;
    private int gameQuantity;
    private String gameCategory;

    @Column(columnDefinition = "BLOB")
    private byte[] image;
}
