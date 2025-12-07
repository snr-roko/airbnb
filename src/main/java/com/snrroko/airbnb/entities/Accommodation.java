package com.snrroko.airbnb.entities;

import com.snrroko.airbnb.entities.enums.AccommodationStatus;
import com.snrroko.airbnb.entities.enums.AccommodationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationType type;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer quantity;

    // prices
    @Column(nullable = false)
    private BigDecimal pricePerNight;

    private BigDecimal pricePerHour;

    private BigDecimal pricePerWeek;

    private BigDecimal pricePerMonth;

    private Double sizeInSqMeter;

    private Integer minimumStayInDays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationStatus status;

    @ElementCollection
    private List<String> amenities = new ArrayList<>();

    @ElementCollection
    private List<String> images = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
