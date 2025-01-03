package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findByOrderNo(String orderNo);

    @Query("SELECT o.orderNo, o.address, o.date_created, os.order_status, oi.quantity, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.gameTitle ELSE oi.accessory.productName END) AS productName, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END) AS productPrice " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN o.orderStatus os")
    Page<Object[]> findAllOrdersWithDetails(Pageable pageable);

    @Query("SELECT orderId, COUNT(orderId) " +
            "FROM Order o "+
            "GROUP BY orderId"
    )
    Integer findTotalOrders();

    @Query("SELECT o.orderNo, o.address, o.date_created, os.order_status, oi.quantity, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.gameTitle ELSE oi.accessory.productName END) AS productName, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END) AS productPrice " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN o.orderStatus os " +
            "WHERE os.order_status LIKE 'completed' "
    )
    Page<Object[]> findOrderWithCompletedStatus(Pageable pageable);

    @Query("SELECT o.orderNo, o.address, o.date_created, os.order_status, oi.quantity, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.gameTitle ELSE oi.accessory.productName END) AS productName, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END) AS productPrice " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN o.orderStatus os " +
            "WHERE os.order_status LIKE 'ongoing' "
    )
    Page<Object[]> findOrderWithPendingStatus(Pageable pageable);

    @Query("SELECT o.orderNo, o.address, o.date_created, os.order_status, oi.quantity, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.gameTitle ELSE oi.accessory.productName END) AS productName, " +
            "(CASE WHEN oi.game IS NOT NULL THEN oi.game.actualPrice ELSE oi.accessory.price END) AS productPrice " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN o.orderStatus os " +
            "WHERE os.order_status LIKE 'canceled' "
    )
    Page<Object[]> findOrderWithCanceledStatus(Pageable pageable);
}


