package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {

    @Query(
            """
                SELECT a
                FROM Accommodation a
                JOIN a.property p
                WHERE (:city IS NULL OR UPPER(p.city) == UPPER(:city))
                AND (:name IS NULL OR UPPER(p.name) LIKE UPPER(%:name%)
                AND (:accommodationType IS NULL OR :accommodationType == a.type)
            """
    )
    Page<Accommodation> searchForAccommodations(
            @Param("city") String city,
            @Param("name") String name,
            @Param("accommodationType") String accommodationType,
            Pageable pageable
    );
}
