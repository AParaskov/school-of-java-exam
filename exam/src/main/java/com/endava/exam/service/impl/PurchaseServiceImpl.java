package com.endava.exam.service.impl;

import com.endava.exam.dto.BuyItemsRequestDto;
import com.endava.exam.entity.Item;
import com.endava.exam.entity.Purchase;
import com.endava.exam.entity.enums.PaymentType;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.repository.PurchaseRepository;
import com.endava.exam.repository.SupermarketRepository;
import com.endava.exam.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final SupermarketRepository supermarketRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Purchase buyItemsFromSupermarket(BuyItemsRequestDto buyItemsRequestDto) {
        Purchase purchase;
        Double totalPrice = Double.parseDouble("0");

        try {
            PaymentType.valueOf(buyItemsRequestDto.getPaymentType());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Payment type does not meet requirements!");
        }

        if (PaymentType.valueOf(buyItemsRequestDto.getPaymentType()).equals(PaymentType.CASH) && buyItemsRequestDto.getCashAmount() == null) {
            throw new IllegalArgumentException("Invalid cash amount!");
        }

        purchase = modelMapper.map(buyItemsRequestDto, Purchase.class);
        purchase.setSupermarket(supermarketRepository.findById(buyItemsRequestDto.getSupermarketId()).orElseThrow(() -> new EntityNotFoundException("Supermarket not found!")));
        purchase.setItems(buyItemsRequestDto.getItemIds().stream().map(id -> itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found!"))).collect(Collectors.toSet()));
        for (Item item : purchase.getItems()) {
            totalPrice += item.getPrice();
        }
        purchase.setTotalPrice(totalPrice);
        purchase.setChangeAmount(Double.parseDouble("0"));
        purchase.setTimeOfPayment(LocalDateTime.now());

        if (PaymentType.valueOf(buyItemsRequestDto.getPaymentType()).equals(PaymentType.CASH)) {
            purchase.setChangeAmount(purchase.getCashAmount() - totalPrice);
        }

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAllPurchases(String pageNumber, String pageSize, String sortBy) {
        if (!(sortBy.equals("id") || sortBy.equals("totalPrice") || sortBy.equals("cashAmount") || sortBy.equals("timeOfPayment"))) {
            throw new IllegalArgumentException("There is no such sorting category!");
        }

        Pageable paging = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), Sort.by(sortBy));
        return this.purchaseRepository
                .findAll(paging)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> purchasesForExport(String sortBy) {
        if (!(sortBy.equals("id") || sortBy.equals("totalPrice") || sortBy.equals("cashAmount") || sortBy.equals("timeOfPayment"))) {
            throw new IllegalArgumentException("There is no such sorting category!");
        }

        return new ArrayList<>(this.purchaseRepository
                .findAll(Sort.by(sortBy)));
    }
}
