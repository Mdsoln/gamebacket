package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.constants.Status;
import com.gamebacket.vercel.app.entity.*;
import com.gamebacket.vercel.app.repo.*;
import com.gamebacket.vercel.app.service.inter.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final OrderStatusRepo orderStatusRepo;
    private final OrderItemRepo orderItemRepo;
    private final GameRepo gameRepo;
    private final AccessoriesRepo accessoriesRepo;

    @Override
    public void saveOrders(String customerEmail, String street, String region,String productName,int productQuantity) {
        try {
            Customer customer = userRepo.findByUserEmail(customerEmail);
            if (customer != null){
                Order order = new Order();
                order.setOrderNo(generateRandomOrderNumber());
                order.setAddress(street+" "+region);
                order.setCustomer(customer);
                orderRepo.save(order);

                OrderStatus status = new OrderStatus();
                status.setOrder_status(Status.STATUS_ONGOING);
                status.setOrder(order);
                orderStatusRepo.save(status);

               if (productName != null){
                   saveOrderItem(order,productName,productQuantity);
               }
            }
        }catch (Exception e){
            throw new RuntimeException("Error while processing an order"+e);
        }
    }

    private void saveOrderItem(Order order, String productName, int productQuantity) {
        try {
            Games game = gameRepo.findByGameTitleIgnoreCase(productName);
            Accessories accessories = accessoriesRepo.findByProductName(productName);
            if (game != null){
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(productQuantity);
                orderItem.setOrder(order);
                orderItem.setGame(game);
                orderItemRepo.save(orderItem);

                game.setGameQuantity(game.getGameQuantity() - 1);
                gameRepo.save(game);

            } else if (accessories != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(productQuantity);
                orderItem.setOrder(order);
                orderItem.setAccessory(accessories);
                orderItemRepo.save(orderItem);

                accessories.setQuantity(accessories.getQuantity() -1);
                accessoriesRepo.save(accessories);
            }
        }catch (Exception exception){
            throw new RuntimeException("Error processing order"+exception);
        }
    }

    private String generateRandomOrderNumber(){
        int orderNumberLength = 5;
        StringBuilder builder = new StringBuilder();

        Random random = new Random();
        for (int i=0; i < orderNumberLength; i++){
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return "233"+builder;
    }

    @Override
    public void cancelOrder(String orderNo) {
          Order order = orderRepo.findByOrderNo(orderNo);
          if (order != null){
                 OrderStatus canceledStatus = order.getOrderStatus();
                 if (canceledStatus != null) {
                     canceledStatus.setOrder_status(Status.STATUS_CANCELED);
                     canceledStatus.setDate_updated(LocalDate.now());
                     orderStatusRepo.save(canceledStatus);

                     order.setDate_updated(LocalDate.now());
                     orderRepo.save(order);
                 }
          }
    }

}
