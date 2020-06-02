package com.example.chat.business.repository;

import com.example.chat.business.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByNameLike(String keyword);
    Room findOne(Long id);
}
