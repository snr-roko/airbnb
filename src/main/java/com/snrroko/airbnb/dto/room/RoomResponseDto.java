package com.snrroko.airbnb.dto.room;

import com.snrroko.airbnb.entities.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomResponseDto {

    private Long id;

    private String type;

    private BigDecimal basePrice;

    private String[] photos;

    private String[] amenities;

    private Integer totalCount;

    private Integer capacity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
