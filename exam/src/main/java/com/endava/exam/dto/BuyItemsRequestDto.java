package com.endava.exam.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BuyItemsRequestDto {
    @NotBlank(message = "Invalid ID")
    private String supermarketId;

    @NotEmpty(message = "Invalid IDs")
    private List<String> itemIds;

    @NotNull(message = "Invalid payment type!")
    private String paymentType;

    private Double cashAmount;
}
