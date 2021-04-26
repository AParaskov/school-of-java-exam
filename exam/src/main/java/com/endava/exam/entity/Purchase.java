package com.endava.exam.entity;

import com.endava.exam.entity.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "supermarket_id")
    private Supermarket supermarket;

    @ManyToMany
    private Set<Item> items;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "cash_amount")
    @Min(value = 0)
    private Double cashAmount;

    @Column(name = "total_price", nullable = false)
    @Min(value = 0)
    private Double totalPrice;

    @Column(name = "change_amount")
    @Min(value = 0)
    private Double changeAmount;

    @Column(name = "time_of_payment", nullable = false)
    private LocalDateTime timeOfPayment;
}
