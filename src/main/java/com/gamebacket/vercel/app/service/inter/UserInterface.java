package com.gamebacket.vercel.app.service.inter;

import java.time.LocalDate;


public interface UserInterface {
    void saveOrders(String customerEmail, String street, String region,String productName,int productQuantity, LocalDate order_date);

    void cancelOrder(String cancelOrder);

}
