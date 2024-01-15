package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus,Long> {
    // TODO: 15/01/2024 return orders depends on their status to the admin page 
}
