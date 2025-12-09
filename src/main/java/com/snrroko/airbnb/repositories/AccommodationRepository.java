package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Accommodation;
import com.snrroko.airbnb.entities.enums.AccommodationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {

//    @Query(
//            """
//                SELECT a
//                FROM Accommodation a
//                JOIN a.property p
//                WHERE (LOWER(p.city) = LOWER(:city) OR :city IS NULL)
//                AND (LOWER(p.name) LIKE LOWER(%:name%) OR :name IS NULL)
//                AND (a.type = :accommodationType OR :accommodationType IS NULL)
//            """
//    )
    @Query(
            """
                SELECT a
                FROM Accommodation a
                JOIN a.property p
                WHERE (p.city = :city OR :city IS NULL)
                AND (LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) OR :name IS NULL)
                AND (a.type = :accommodationType OR :accommodationType IS NULL)
            """
    )
    Page<Accommodation> searchForAccommodations(
            @Param("city") String city,
            @Param("name") String name,
            @Param("accommodationType") AccommodationType accommodationType,
            Pageable pageable
    );
}
