package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.dto.SalesReport;
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

    //total sales per day
    @Query("SELECT SUM(oi.quantity * (CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END)) AS totalSales, order.date_created AS dateCreated " +
            "FROM OrderItem oi " +
            "JOIN oi.order order " +
            "GROUP BY order.date_created " +
            "ORDER BY dateCreated ASC")
    List<SalesReport> findTotalSalesPerDay();

    //total sales per week
    @Query("SELECT SUM(oi.quantity * (CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END)) AS totalSales, YEAR(order.date_created) AS year, WEEKOFYEAR(order.date_created) AS week " +
            "FROM OrderItem oi " +
            "JOIN oi.order order " +
            "GROUP BY YEAR(order.date_created), WEEKOFYEAR(order.date_created) " +
            "ORDER BY year ASC, week ASC")
    List<SalesReport> findTotalSalesPerWeek();

}
