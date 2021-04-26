package com.endava.exam.service.impl;

import com.endava.exam.dto.CreateItemRequestDto;
import com.endava.exam.entity.Item;
import com.endava.exam.entity.enums.ItemType;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Item createItem(CreateItemRequestDto createItemRequestDto) {
        try {
            ItemType.valueOf(createItemRequestDto.getItemType());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Item type does not meet requirements!");
        }

        Item item = this.modelMapper.map(createItemRequestDto, Item.class);
        return this.itemRepository.save(item);
    }
}
