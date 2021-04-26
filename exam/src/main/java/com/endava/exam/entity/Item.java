package com.endava.exam.entity;

import com.endava.exam.entity.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Supermarket supermarket;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private Set<Purchase> purchases;
}
