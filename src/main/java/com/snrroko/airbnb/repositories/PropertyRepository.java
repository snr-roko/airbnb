package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
}
