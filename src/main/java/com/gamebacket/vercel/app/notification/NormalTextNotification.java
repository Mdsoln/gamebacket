package com.gamebacket.vercel.app.notification;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NormalTextNotification {
    private final ApiClient apiClient;

    public ResponseEntity<String> sendTextNotification(String customerEmail, String productName){
        SmsApi smsApi = new SmsApi(apiClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Hey (First Name) "+buildNotificationMessage(customerEmail,productName));
        smsMessage.listId(2466247);
        smsMessage.source("Spring Boot Application");

        List<SmsMessage> smsMessageList = List.of(smsMessage);

        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);

        try {
            String result = smsApi.smsSendPost(smsMessages);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();

        }

        return new ResponseEntity<>("Exception when calling SmsApi#smsSendPost", HttpStatus.BAD_REQUEST);
    }

    private String buildNotificationMessage(String customerEmail, String productName) {
        StringBuilder message = new StringBuilder();
        message.append("A new order has been placed on Gamebacket.\n");
        message.append("Customer Email: ").append(customerEmail).append("\n");
        message.append("Product Name: ").append(productName).append("\n");

        return message.toString();
    }

    // TODO: 04/03/2024 need to add the username for github

}
