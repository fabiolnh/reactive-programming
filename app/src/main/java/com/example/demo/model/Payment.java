package com.example.demo.model;

import com.example.demo.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Payment {

    private String id;
    private String userId;
    private PaymentStatusEnum status;

}
