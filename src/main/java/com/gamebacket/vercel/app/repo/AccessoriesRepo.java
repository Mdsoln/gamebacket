package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Accessories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoriesRepo extends JpaRepository<Accessories,Long> {

    Accessories findByProductName(String accessoryName);

    @Query("SELECT a.productName, a.quantity, a.category, a.price, COUNT(o.orderId) AS TotalOrder " +
            "FROM Accessories a " +
            "LEFT JOIN a.orderItem oi " +
            "LEFT JOIN oi.order o " +
            "GROUP BY a.productName, a.quantity, a.category, a.price "
    )
    Page<Object[]> findAllAccessoriesWithOrders(Pageable pageable);

}
