package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.exc.SearchExceptions;
import com.gamebacket.vercel.app.repo.AccessoriesRepo;
import com.gamebacket.vercel.app.repo.GameRepo;
import com.gamebacket.vercel.app.repo.OrderRepo;
import com.gamebacket.vercel.app.repo.UserRepo;
import com.gamebacket.vercel.app.service.inter.SearchInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService implements SearchInterface {
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    @Override
    public List<Games> findByGameTitleContainingIgnoreCase(String queryGames) {
        try {
            return gameRepo.findByGameTitleContainingIgnoreCase(queryGames);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public List<String> findAllGamesTitle() {
        try {
            return gameRepo.findAllGamesTitle();
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public Page<Object[]> findAllCustomers(Pageable pageable) {
        try {
            return userRepo.findAllCustomers(pageable);
        }catch (DataAccessException e){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public Page<Object[]> findAllGamesWithOrders(Pageable pageable) {
        try {
            return gameRepo.findAllGamesWithOrders(pageable);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public Page<Object[]> findAllAccessoriesWithOrders(Pageable pageable) {
        try {
            return accessoriesRepo.findAllAccessoriesWithOrders(pageable);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public Page<Object[]> findAllOrdersWithDetails(Pageable pageable) {
        try {
            return orderRepo.findAllOrdersWithDetails(pageable);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @Override
    public int countAllOrders() {
        try {
            return orderRepo.findTotalOrders();
        }catch (DataAccessException exception){
            throw new SearchExceptions("Oops! something went wrong");
        }
    }

    @Override
    public Page<Object[]> findCompleteOrders(Pageable pageable) {
        try {
            return orderRepo.findOrderWithCompletedStatus(pageable);
        }catch (DataAccessException d){
            throw new SearchExceptions("Error occurred!");
        }
    }

    @Override
    public Page<Object[]> findCanceledOrders(Pageable pageable) {
       try {
           return orderRepo.findOrderWithCanceledStatus(pageable);
       }catch (DataAccessException d){
           throw new SearchExceptions("Error occurred!");
       }
    }

    @Override
    public Page<Object[]> findOngoingOrders(Pageable pageable) {
       try {
           return orderRepo.findOrderWithPendingStatus(pageable);
       }catch (DataAccessException d){
           throw new SearchExceptions("Error occurred");
       }
    }
}
