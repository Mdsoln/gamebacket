package com.gamebacket.vercel.app.controller;


import com.gamebacket.vercel.app.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@CrossOrigin()
@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @CrossOrigin()
    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(
            @RequestParam(name = "email") String customerEmail,
            @RequestParam(name = "street") String street,
            @RequestParam(name = "region") String region,
            @RequestParam(name = "productName") String productName,
            @RequestParam(name = "productQuantity") int productQuantity
            ){
        userService.saveOrders(customerEmail,street,region,productName,productQuantity);
        return ResponseEntity.ok("You have successfully placed your order");
    }

    @CrossOrigin()
    @PostMapping("/cancel-order/{orderNo}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderNo") String orderNo){
        userService.cancelOrder(orderNo);
        return ResponseEntity.ok("Oops! You have cancel your order, you may still have time to make it happen");
    }
}
