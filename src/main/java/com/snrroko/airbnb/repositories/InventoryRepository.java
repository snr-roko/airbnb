package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
