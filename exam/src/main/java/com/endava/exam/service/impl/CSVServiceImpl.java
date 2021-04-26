package com.endava.exam.service.impl;

import com.endava.exam.dto.ExportPurchasesDto;
import com.endava.exam.service.CSVService;
import com.endava.exam.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CSVServiceImpl implements CSVService {
    private final ModelMapper modelMapper;
    private final PurchaseService purchaseService;

    @Override
    public void writePurchases(String sortBy, Writer writer) throws IOException {
        String[] csvHeader = {"Id", "Supermarket", "Items", "PaymentType", "CashAmount", "TotalPrice", "ChangeAmount", "TimeOfPayment"};
        String[] nameMapping = {"id", "supermarketName", "itemNames", "paymentType", "cashAmount", "totalPrice", "changeAmount", "timeOfPayment"};

        List<ExportPurchasesDto> purchases = purchaseService.purchasesForExport(sortBy).stream()
                .map(purchase -> {
                    ExportPurchasesDto purchasesDto = modelMapper.map(purchase, ExportPurchasesDto.class);
                    purchasesDto.setSupermarketName(purchase.getSupermarket().getName());
                    purchasesDto.setItemNames(purchase.getItems().stream().map(item -> item.getName()).collect(Collectors.toList()));
                    purchasesDto.setPaymentType(purchase.getPaymentType().name());
                    purchasesDto.setCashAmount(purchase.getCashAmount().toString());
                    purchasesDto.setTotalPrice(purchase.getTotalPrice().toString());
                    purchasesDto.setChangeAmount(purchase.getChangeAmount().toString());
                    purchasesDto.setTimeOfPayment(purchase.getTimeOfPayment().toString());
                    return purchasesDto;
                })
                .collect(Collectors.toList());


        ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        beanWriter.writeHeader(csvHeader);

        for (ExportPurchasesDto purchasesDto : purchases) {
            beanWriter.write(purchasesDto, nameMapping);
        }

        beanWriter.close();
    }
}
