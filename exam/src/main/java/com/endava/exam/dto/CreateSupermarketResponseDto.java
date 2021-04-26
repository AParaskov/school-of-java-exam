package com.endava.exam.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateSupermarketResponseDto {
    private String name;

    private String address;

    private String phoneNumber;

    private String workHours;
}
