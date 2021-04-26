package com.endava.exam.dto;

import com.endava.exam.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GetSupermarketByIdResponseDto {
    private String name;

    private String address;

    private String phoneNumber;

    private String workHours;

    private Set<Item> items;
}
