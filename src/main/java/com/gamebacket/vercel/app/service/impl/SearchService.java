package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.entity.Customer;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.repo.AccessoriesRepo;
import com.gamebacket.vercel.app.repo.GameRepo;
import com.gamebacket.vercel.app.repo.UserRepo;
import com.gamebacket.vercel.app.service.inter.SearchInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService implements SearchInterface {
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;
    private final UserRepo userRepo;

    @Override
    public List<Games> findByGameTitleContainingIgnoreCase(String queryGames) {
        try {
            return gameRepo.findByGameTitleContainingIgnoreCase(queryGames);
        }catch (DataAccessException exception){
            throw new RuntimeException("An error occurred while executing the query.",exception);
        }
    }

    @Override
    public List<String> findAllGamesTitle() {
        try {
            return gameRepo.findAllGamesTitle();
        }catch (DataAccessException exception){
            throw new RuntimeException("An error occurred while executing the query.",exception);
        }
    }

    @Override
    public List<Object[]> findAllCustomers() {
        try {
            return userRepo.findAllCustomers();
        }catch (DataAccessException e){
            throw new RuntimeException("Oops!! No users by now");
        }
    }
}
