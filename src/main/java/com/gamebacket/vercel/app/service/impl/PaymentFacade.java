package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.constants.PaymentProviderType;
import com.gamebacket.vercel.app.dto.PaymentRequest;
import com.gamebacket.vercel.app.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {
    private final AirtelMoneyServiceImpl airtelMoneyService;
    private final TigoPesaServiceImpl tigoPesaService;
    private final GePGServiceImpl gePGService;

    public PaymentResponse pay(PaymentRequest request){
        PaymentProviderType providerType = request.getProviderType();
        return switch (providerType) {
            case GEPG -> gePGService.pay(request);
            case TIGO_PESA -> tigoPesaService.pay(request);
            case AIRTEL_MONEY -> airtelMoneyService.pay(request);
        };
    }
}
