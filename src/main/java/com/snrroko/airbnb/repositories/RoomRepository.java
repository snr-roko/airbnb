package com.snrroko.airbnb.repositories;

import com.snrroko.airbnb.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
