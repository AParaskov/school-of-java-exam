package com.endava.exam.service;

import com.endava.exam.dto.CreateItemRequestDto;
import com.endava.exam.entity.Item;

public interface ItemService {
    Item createItem(CreateItemRequestDto createItemRequestDto);
}
