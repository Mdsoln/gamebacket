package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoriesRepo extends JpaRepository<Accessories,Long> {

}
