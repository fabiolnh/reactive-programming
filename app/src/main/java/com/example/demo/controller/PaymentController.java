package com.example.demo.controller;

import com.example.demo.controller.dto.NewPaymentInput;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController
{
    final private PaymentRepository paymentRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Payment> createPayment(@RequestBody NewPaymentInput input) {
        final String userId = input.getUserId();
        log.info("Payment to be processed. {}", userId);

        return this.paymentRepository.createPayment(userId)
                .doOnNext(next -> log.info("Payment Processed"));
    }

}
