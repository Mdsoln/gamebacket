package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Games,Long> {
}
