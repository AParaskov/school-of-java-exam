package com.endava.exam.service.impl;

import com.endava.exam.dto.AddItemToSupermarketRequestDto;
import com.endava.exam.dto.CreateSupermarketRequestDto;
import com.endava.exam.entity.Item;
import com.endava.exam.entity.Supermarket;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.repository.SupermarketRepository;
import com.endava.exam.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {
    private final SupermarketRepository supermarketRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;


    @Override
    public Supermarket createSupermarket(CreateSupermarketRequestDto createSupermarketRequestDto) {
        Supermarket supermarket = this.modelMapper.map(createSupermarketRequestDto, Supermarket.class);
        return this.supermarketRepository.save(supermarket);
    }

    @Override
    public Supermarket addItemsToSupermarket(AddItemToSupermarketRequestDto addItemToSupermarketRequestDto) {
        Supermarket supermarket = this.supermarketRepository.findById(addItemToSupermarketRequestDto.getSupermarketId())
                .orElseThrow(() -> new EntityNotFoundException("Supermarket not found!"));

        Set<Item> items = supermarket.getItems();

        for (String itemId : addItemToSupermarketRequestDto.getItemIds()) {
            Item item = this.itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("Item not found!"));
            item.setSupermarket(supermarket);
            items.add(item);
        }

        supermarket.setItems(items);
        return this.supermarketRepository.saveAndFlush(supermarket);
    }

    @Override
    public Supermarket getSupermarketById(String id) {
        return this.supermarketRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supermarket not found!"));
    }


}
