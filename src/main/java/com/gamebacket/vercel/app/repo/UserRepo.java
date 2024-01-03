package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Customer,Long> {

}
