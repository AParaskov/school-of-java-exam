package com.endava.exam.controller;

import com.endava.exam.dto.CreateItemRequestDto;
import com.endava.exam.dto.CreateItemResponseDto;
import com.endava.exam.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ModelMapper modelMapper;
    private final ItemService itemService;

    @PostMapping(value = "/create-item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateItemResponseDto> createItem(@Valid @RequestBody CreateItemRequestDto createItemRequestDto) {
        return new ResponseEntity<>(modelMapper.map(itemService.createItem(createItemRequestDto), CreateItemResponseDto.class), HttpStatus.CREATED);
    }
}
