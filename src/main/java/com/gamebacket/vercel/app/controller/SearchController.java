package com.gamebacket.vercel.app.controller;


import com.gamebacket.vercel.app.constants.PageResponse;
import com.gamebacket.vercel.app.constants.Status;
import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.exc.SearchExceptions;
import com.gamebacket.vercel.app.service.impl.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    /**
     * searching games based on user input and returning all games that contain user input
     */
    @GetMapping("/games")
    public ResponseEntity<List<Games>> searchGameByName(@RequestParam String queryGames){
        List<Games> matchingGames = searchService.findByGameTitleContainingIgnoreCase(queryGames);
        return ResponseEntity.ok(matchingGames);
    }

    /**
     * Autocomplete search suggestions
     */
    @GetMapping("/game-options")
    public ResponseEntity<List<String>> gameGameOptions(){
        List<String> gameOptions = searchService.findAllGamesTitle();
        return ResponseEntity.ok(gameOptions);
    }

    @GetMapping("/users-with-orders")
    public ResponseEntity<PageResponse<Object[]>> getRegisteredUsers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ){
        try {
            if (pageNumber < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Invalid pageNumber or pageSize values");
            }

            Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("full_name"));
            Page<Object[]> customers = searchService.findAllCustomers(pageable);

            PageResponse<Object[]> response = new PageResponse<>(
                    customers.getContent(),
                    customers.getNumber(),
                    customers.getTotalPages(),
                    customers.getTotalElements()
            );

            return ResponseEntity.ok(response);

        }catch (DataAccessException exception){
            throw new SearchExceptions("Unexpected query parameter");
        }
    }


    @GetMapping("/games-with-orders")
    public ResponseEntity<PageResponse<Object[]>> gamesWithTotalOrders(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ){
        try {
            if (pageNumber < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Invalid pageNumber or pageSize values");
            }

            Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by("gameTitle"));
            Page<Object[]> gamesWithOrders = searchService.findAllGamesWithOrders(pageable);

            PageResponse<Object[]> gameResponse = new PageResponse<>(
                    gamesWithOrders.getContent(),
                    gamesWithOrders.getNumber(),
                    gamesWithOrders.getTotalPages(),
                    gamesWithOrders.getTotalElements()
            );

            return ResponseEntity.ok(gameResponse);

        }catch (DataAccessException e){
            throw new SearchExceptions("Unexpected query parameter");
        }
    }

    @GetMapping("/accessories-with-orders")
    public ResponseEntity<PageResponse<Object[]>> accessoriesWithOrders(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ){
        try {
            if (pageNumber < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Invalid pageNumber or pageSize values");
            }

            Pageable pageable = PageRequest.of(pageNumber,pageSize);
            Page<Object[]> accessoriesWithOrders = searchService.findAllAccessoriesWithOrders(pageable);

            PageResponse<Object[]> response = new PageResponse<>(
                    accessoriesWithOrders.getContent(),
                    accessoriesWithOrders.getNumber(),
                    accessoriesWithOrders.getTotalPages(),
                    accessoriesWithOrders.getTotalElements()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (DataAccessException exception){
            throw new SearchExceptions("Unexpected query parameter");
        }
    }

    @GetMapping("/orders-with-products")
    public ResponseEntity<PageResponse<Object[]>> ordersWithOrderedProducts(
          @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
          @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ){
        try {
            if (pageNumber < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Invalid pageNumber or pageSize values");
            }

            Pageable pageable = PageRequest.of(pageNumber,pageSize);
            Page<Object[]> orderDetails = searchService.findAllOrdersWithDetails(pageable);

            PageResponse<Object[]> response = new PageResponse<>(
                    orderDetails.getContent(),
                    orderDetails.getNumber(),
                    orderDetails.getTotalPages(),
                    orderDetails.getTotalElements()
            );
            return ResponseEntity.ok(response);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to fetch");
        }
    }

    @GetMapping("/count-orders")
    public ResponseEntity<Integer> countOrders(){
        int orders = searchService.countAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status-of-orders")
    public ResponseEntity<PageResponse<Object[]>> findByOrderByStatus(
            @RequestParam String actionStatus,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ){
        try {

            if (pageNumber < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Invalid pageNumber or pageSize values");
            }

            Pageable pageable = PageRequest.of(pageNumber,pageSize);

            if (Status.ACTION_COMPLETE.equalsIgnoreCase(actionStatus)){
                Page<Object[]> completedOrders = searchService.findCompleteOrders(pageable);

                PageResponse<Object[]> completeOrdersResponse = new PageResponse<>(
                        completedOrders.getContent(),
                        completedOrders.getNumber(),
                        completedOrders.getTotalPages(),
                        completedOrders.getTotalElements()
                );
                return ResponseEntity.ok(completeOrdersResponse);
            } else if (Status.ACTION_CANCEL.equalsIgnoreCase(actionStatus)) {
                Page<Object[]> canceledOrders = searchService.findCanceledOrders(pageable);

                PageResponse<Object[]> canceledOrdersResponse = new PageResponse<>(
                        canceledOrders.getContent(),
                        canceledOrders.getNumber(),
                        canceledOrders.getTotalPages(),
                        canceledOrders.getTotalElements()
                );
                return ResponseEntity.ok(canceledOrdersResponse);
            } else if (Status.ACTION_PENDING.equalsIgnoreCase(actionStatus)) {
                Page<Object[]> pendingOrders = searchService.findOngoingOrders(pageable);

                PageResponse<Object[]> pendingOrdersResponse = new PageResponse<>(
                        pendingOrders.getContent(),
                        pendingOrders.getNumber(),
                        pendingOrders.getTotalPages(),
                        pendingOrders.getTotalElements()
                );
                return ResponseEntity.ok(pendingOrdersResponse);
            }
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }catch (DataAccessException exception){
            throw new SearchExceptions("Failed to retrieve");
        }
    }
}

