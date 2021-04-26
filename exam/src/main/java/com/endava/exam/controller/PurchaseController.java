package com.endava.exam.controller;

import com.endava.exam.dto.BuyItemsRequestDto;
import com.endava.exam.dto.BuyItemsResponseDto;
import com.endava.exam.dto.GetAllPurchasesResponseDto;
import com.endava.exam.service.CSVService;
import com.endava.exam.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final ModelMapper modelMapper;
    private final PurchaseService purchaseService;
    private final CSVService csvService;

    @PostMapping(value = "/buy-items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyItemsResponseDto> buyItems(@Valid @RequestBody BuyItemsRequestDto buyItemsRequestDto) {
        return new ResponseEntity<>(modelMapper.map(purchaseService.buyItemsFromSupermarket(buyItemsRequestDto), BuyItemsResponseDto.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-all-purchases")
    public ResponseEntity<List<GetAllPurchasesResponseDto>> getAllPurchases(@Valid @Min(value = 0, message = "Page number cannot be negative!")
                                                                            @Pattern(regexp = "-?(0|[1-9]\\d*)", message = "Please enter a valid page number!")
                                                                            @RequestParam("pageNumber") String pageNumber,
                                                                            @Valid @Min(value = 1, message = "Page size cannot be negative or zero!")
                                                                            @Pattern(regexp = "-?(0|[1-9]\\d*)", message = "Please enter a valid page size!")
                                                                            @RequestParam("pageSize") String pageSize,
                                                                            @Valid @NotBlank(message = "Sort cannot be empty!")
                                                                            @RequestParam("sortBy") String sortBy) {
        return ResponseEntity.ok(purchaseService.getAllPurchases(pageNumber, pageSize, sortBy).stream().map(purchase -> modelMapper.map(purchase, GetAllPurchasesResponseDto.class)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/export-purchases", produces = "text/csv")
    public void exportPurchases(@Valid @NotBlank(message = "Sort cannot be empty!")
                                @RequestParam("sortBy") String sortBy, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=books_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        csvService.writePurchases(sortBy, response.getWriter());
    }

}
