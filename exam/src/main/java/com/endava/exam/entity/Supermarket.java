package com.endava.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "supermarkets")
public class Supermarket extends BaseEntity {
    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "work_hours", nullable = false)
    private String workHours;

    @OneToMany(mappedBy = "supermarket", cascade = CascadeType.ALL)
    private Set<Item> items;

    @OneToMany(mappedBy = "supermarket", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Purchase> purchases;
}
