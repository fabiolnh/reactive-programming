package com.example.demo.controller.dto;

import com.example.demo.enums.PaymentStatusEnum;
import lombok.Data;

@Data
public class NewPaymentInput {
    private String id;
    private String userId;
    private PaymentStatusEnum status;
}
