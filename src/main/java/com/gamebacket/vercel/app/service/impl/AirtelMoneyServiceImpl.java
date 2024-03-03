package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.dto.PaymentRequest;
import com.gamebacket.vercel.app.dto.PaymentResponse;
import com.gamebacket.vercel.app.service.inter.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirtelMoneyServiceImpl implements PaymentService<PaymentRequest, PaymentResponse> {
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        return null;
    }
    //here API gateway from Airtel will be process to accomplish payments
}
// TODO: 03/03/2024 extracting response from vendors