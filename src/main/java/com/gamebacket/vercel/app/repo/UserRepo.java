package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Customer,Long> {
    Customer findByUserEmail(String email);


    @Query("SELECT c.full_name, c.userEmail, c.date_created, COUNT(o) " +
            "FROM Customer c " +
            "JOIN c.orders o " +
            "GROUP BY c.full_name, c.userEmail "
    )
    Page<Object[]> findAllCustomers(Pageable pageable);

    //total number of users registered per day
    @Query("SELECT COUNT(userId) AS Total, date_created " +
            "FROM Customer " +
            "WHERE date_created >= :startDate AND date_created <= :endDate " +
            "GROUP BY date_created"
    )
    int findTotalRegisteredUsersByDateRange(LocalDate startDate, LocalDate endDate);

    //users registered regardless of date
    @Query("SELECT COUNT(userId) AS Total, date_created " +
            "FROM Customer " +
            "GROUP BY date_created"
    )
    List<Object[]> findTotalRegisteredUsersByDateRange();

    //total number of users login to their accounts
    //total number of users visits the website
}
