package com.snrroko.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
        name = "unique_inventory",
        columnNames = {"hotel_id", "room_id", "date"}
))
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedcount;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
