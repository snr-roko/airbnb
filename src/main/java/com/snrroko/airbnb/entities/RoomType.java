package com.snrroko.airbnb.entities;

import com.snrroko.airbnb.entities.enums.RoomSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "room_type_name_size_unique",
                        columnNames = {"name", "size"}
                )
        }
)
@Getter
@Setter
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms = new HashSet<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal surgeFactor;

    @Column(columnDefinition = "TEXT[]")
    private String[] amenities;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomSize size = RoomSize.valueOf("SMALL");

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
