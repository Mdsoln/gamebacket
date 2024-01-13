package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.entity.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchInterface {
    List<Games> findByGameTitleContainingIgnoreCase(String queryGames);

    List<String> findAllGamesTitle();

    Page<Object[]> findAllCustomers(Pageable pageable);

    Page<Object[]> findAllGamesWithOrders(Pageable pageable);

    Page<Object[]> findAllAccessoriesWithOrders(Pageable pageable);

}
