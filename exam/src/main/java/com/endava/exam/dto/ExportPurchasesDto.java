package com.endava.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExportPurchasesDto {
    private String id;

    private String supermarketName;

    private List<String> itemNames;

    private String paymentType;

    private String cashAmount;

    private String totalPrice;

    private String changeAmount;

    private String timeOfPayment;
}
