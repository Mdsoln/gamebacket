package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Games,Long> {
    List<Games> findByGameTitleContainingIgnoreCase(String queryGames);

    @Query("SELECT DISTINCT g.gameTitle FROM Games g")
    List<String> findAllGamesTitle();

    Games findByGameTitleIgnoreCase(String gameName);

    @Query("SELECT g.gameTitle, g.gameQuantity, g.genre, g.actualPrice, COUNT(o.orderId) AS TotalOrders " +
            "FROM Games g " +
            "LEFT JOIN g.orderItem oi " +
            "LEFT JOIN oi.order o " +
            "GROUP BY g.gameTitle, g.gameQuantity, g.genre, g.actualPrice "
    )
    Page<Object[]> findAllGamesWithOrders(Pageable pageable);

}
