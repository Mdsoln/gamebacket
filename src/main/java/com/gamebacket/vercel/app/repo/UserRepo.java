package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Customer,Long> {
    Customer findByUserEmail(String email);


    @Query("SELECT c.full_name, c.userEmail, c.date_created, COUNT(o) " +
            "FROM Customer c " +
            "JOIN c.orders o " +
            "GROUP BY c.full_name, c.userEmail "
    )
    Page<Object[]> findAllCustomers(Pageable pageable);


}
