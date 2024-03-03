package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.dto.PaymentRequest;
import com.gamebacket.vercel.app.dto.PaymentResponse;

public interface PaymentService <T extends PaymentRequest, R extends PaymentResponse>{
    R pay(T request);
}
// TODO: 03/03/2024 processing payments 