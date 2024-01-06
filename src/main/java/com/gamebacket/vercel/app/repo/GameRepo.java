package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Games,Long> {
    List<Games> findByGameTitleContainingIgnoreCase(String queryGames);

    @Query("SELECT DISTINCT g.gameTitle FROM Games g")
    List<String> findAllGamesTitle();
}
