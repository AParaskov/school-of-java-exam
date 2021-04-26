package com.endava.exam.controller;

import com.endava.exam.dto.*;
import com.endava.exam.entity.Item;
import com.endava.exam.entity.Supermarket;
import com.endava.exam.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/v1/supermarkets")
@RequiredArgsConstructor
public class SupermarketController {
    private final ModelMapper modelMapper;
    private final SupermarketService supermarketService;

    @PostMapping(value = "/create-supermarket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateSupermarketResponseDto> createSupermarket(@Valid @RequestBody CreateSupermarketRequestDto createSupermarketRequestDto) {
        return new ResponseEntity<>(modelMapper.map(supermarketService.createSupermarket(createSupermarketRequestDto), CreateSupermarketResponseDto.class), HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-items-to-supermarket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddItemToSupermarketResponseDto> addItemsToSupermarket(@Valid @RequestBody AddItemToSupermarketRequestDto addItemToSupermarketRequestDto) {
        Supermarket supermarket = supermarketService.addItemsToSupermarket(addItemToSupermarketRequestDto);
        AddItemToSupermarketResponseDto responseDto = new AddItemToSupermarketResponseDto();
        responseDto.setSupermarketId(supermarket.getId());
        responseDto.setItemName(supermarket.getItems().stream().map(Item::getName).collect(Collectors.toList()));
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-supermarket-by-id")
    public ResponseEntity<GetSupermarketByIdResponseDto> getSupermarketById(@Valid @NotBlank(message = "Invalid ID!") @RequestParam("id") String id) {
        return ResponseEntity.ok(modelMapper.map(supermarketService.getSupermarketById(id), GetSupermarketByIdResponseDto.class));
    }
}
