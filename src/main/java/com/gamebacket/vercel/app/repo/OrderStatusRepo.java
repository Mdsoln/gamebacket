package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus,Long> {

}
