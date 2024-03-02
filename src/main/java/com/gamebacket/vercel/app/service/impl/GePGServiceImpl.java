package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.dto.PaymentRequest;
import com.gamebacket.vercel.app.dto.PaymentResponse;
import com.gamebacket.vercel.app.service.inter.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GePGServiceImpl implements PaymentService<PaymentRequest, PaymentResponse> {
    @Override
    public PaymentResponse pay(PaymentRequest gePGRequest) {
        return null;
    }
    //here API gateway from GePG will be process to accomplish payments
}
