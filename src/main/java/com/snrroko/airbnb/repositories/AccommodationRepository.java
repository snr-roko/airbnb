package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {
}
