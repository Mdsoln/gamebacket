package com.gamebacket.vercel.app.controller;

import com.gamebacket.vercel.app.dto.PaymentRequest;
import com.gamebacket.vercel.app.dto.PaymentResponse;
import com.gamebacket.vercel.app.service.impl.PaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFacade paymentFacade;

    @PostMapping("/makePayment")
    public ResponseEntity<PaymentResponse> payments(@RequestBody PaymentRequest request){
        PaymentResponse paymentResponse = paymentFacade.pay(request);
        return ResponseEntity.ok(paymentResponse);
    }
}
// TODO: 03/03/2024 managing an endpoint 