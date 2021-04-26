package com.endava.exam.service;

import com.endava.exam.dto.BuyItemsRequestDto;
import com.endava.exam.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase buyItemsFromSupermarket(BuyItemsRequestDto buyItemsRequestDto);

    List<Purchase> getAllPurchases(String pageNumber, String pageSize, String sortBy);

    List<Purchase> purchasesForExport(String sortBy);
}
