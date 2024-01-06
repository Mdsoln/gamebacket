package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.entity.Games;

import java.util.List;

public interface SearchInterface {
    List<Games> findByGameTitleContainingIgnoreCase(String queryGames);

    List<String> findAllGamesTitle();

}
