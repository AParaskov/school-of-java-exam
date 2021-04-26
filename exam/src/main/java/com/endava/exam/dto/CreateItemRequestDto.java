package com.endava.exam.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateItemRequestDto {
    @NotBlank(message = "Name is required!")
    @Length(max = 64, message = "Name does not meet requirements!")
    private String name;

    @NotNull(message = "Invalid price!")
    @DecimalMin(value = "0.01", message = "Price cannot be negative or zero!")
    @DecimalMax(value = "9999.99", message = "Price does not meet requirements!")
    private Double price;

    @NotNull(message = "Invalid item type!")
    private String itemType;
}
