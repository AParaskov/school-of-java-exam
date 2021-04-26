package com.endava.exam.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateSupermarketRequestDto {
    @NotBlank(message = "Invalid name!")
    @Length(max = 64, message = "Name does not meet requirements!")
    private String name;

    @NotBlank(message = "Invalid address!")
    @Length(max = 128, message = "Address does not meet requirements!")
    private String address;

    @NotBlank(message = "Invalid phone number!")
    @Pattern(regexp = "^08[7-9][0-9]{7}", message = "Phone number does not meet requirements!")
    private String phoneNumber;

    @NotBlank(message = "Invalid work hours!")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Work hours do not meet requirements!")
    private String workHours;
}
