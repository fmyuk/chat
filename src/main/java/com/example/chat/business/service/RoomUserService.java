package com.example.chat.business.service;

import com.example.chat.business.entity.RoomUser;

import java.util.List;

public interface RoomUserService {
    void save(RoomUser roomUser, Long roomId, Long userId);

    List<RoomUser> findAllByUserId(Long userId);
}
