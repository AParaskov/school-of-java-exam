package com.endava.exam.service;

import com.endava.exam.dto.AddItemToSupermarketRequestDto;
import com.endava.exam.dto.CreateSupermarketRequestDto;
import com.endava.exam.entity.Supermarket;

public interface SupermarketService {
    Supermarket createSupermarket(CreateSupermarketRequestDto createSupermarketRequestDto);

    Supermarket addItemsToSupermarket(AddItemToSupermarketRequestDto addItemToSupermarketRequestDto);

    Supermarket getSupermarketById(String id);
}
