package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Room;
import com.snrroko.airbnb.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
