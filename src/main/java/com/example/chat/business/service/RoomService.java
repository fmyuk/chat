package com.example.chat.business.service;

import com.example.chat.business.entity.Room;
import com.example.chat.security.LoginUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;

import java.util.List;


public interface RoomService {
    void save(Room room);

    Room findOne(Long id);

    Page<Room> findAll(Pageable pageable);

    List<Room> findAll();

    List<Room> findAllByNameLike(String keyword);

    List<Room> ifUserSignIn(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Pageable pageable, Model model);
}
