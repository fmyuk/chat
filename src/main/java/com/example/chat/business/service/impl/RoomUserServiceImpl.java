package com.example.chat.business.service.impl;

import com.example.chat.business.entity.RoomUser;
import com.example.chat.business.repository.RoomUserRepository;
import com.example.chat.business.service.RoomService;
import com.example.chat.business.service.RoomUserService;
import com.example.chat.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomUserServiceImpl implements RoomUserService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomUserRepository roomUserRepository;

    @Override
    public void save(RoomUser roomUser, Long roomId, Long userId) {
        roomUser.setRoom(roomService.findOne(roomId));
        roomUser.setUser(userService.findOne(userId));
        roomUserRepository.save(roomUser);
    }

    @Override
    public List<RoomUser> findAllByUserId(Long userId) {
        return roomUserRepository.findAllByUserId(userId);
    }
}
