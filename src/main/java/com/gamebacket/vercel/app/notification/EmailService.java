package com.gamebacket.vercel.app.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    private static final String supplierEmail = "********@gmail.com";
    public void sendMailToSuppliers(String customerEmail, String productName){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(supplierEmail);
        simpleMailMessage.setSubject("New Order from Gamebacket");
        simpleMailMessage.setText(buildNotificationMessage(customerEmail,productName));
        javaMailSender.send(simpleMailMessage);
    }

    private String buildNotificationMessage(String customerEmail, String productName) {
        StringBuilder message = new StringBuilder();
        message.append("A new order has been placed on Gamebacket.\n");
        message.append("Customer Email: ").append(customerEmail).append("\n");
        message.append("Product Name: ").append(productName).append("\n");

        return message.toString();
    }
}
