package com.endava.exam.dto;

import com.endava.exam.entity.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemResponseDto {
    private String name;

    private Double price;

    private ItemType itemType;
}
