package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.dto.TopSellingProductDTO;
import com.gamebacket.vercel.app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {

    @Query("SELECT " +
            "CASE WHEN oi.game IS NOT NULL THEN oi.game.gameTitle ELSE oi.accessory.productName END AS productName, " +
            "CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END AS price, " +
            "CASE WHEN oi.game IS NOT NULL THEN oi.game.image ELSE oi.accessory.image END AS image, " +
            "COUNT(oi.orderItemId) AS orderCount " +
            "FROM OrderItem oi " +
            "GROUP BY oi.game, oi.accessory " +
            "ORDER BY orderCount DESC"
    )
    List<TopSellingProductDTO> findTopSellingProducts();

}
