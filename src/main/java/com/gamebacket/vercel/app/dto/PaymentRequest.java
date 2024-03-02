package com.gamebacket.vercel.app.dto;

import com.gamebacket.vercel.app.constants.PaymentProviderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {
    private PaymentProviderType providerType;
    private String paymentId;
    private double amount;
}
