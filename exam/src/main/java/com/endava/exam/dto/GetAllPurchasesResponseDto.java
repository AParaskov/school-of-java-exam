package com.endava.exam.dto;

import com.endava.exam.entity.Item;
import com.endava.exam.entity.Supermarket;
import com.endava.exam.entity.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class GetAllPurchasesResponseDto {
    private String id;

    private Supermarket supermarket;

    private Set<Item> items;

    private PaymentType paymentType;

    private Double cashAmount;

    private Double totalPrice;

    private Double changeAmount;

    private LocalDateTime timeOfPayment;
}
