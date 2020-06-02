package com.example.chat.business.service.impl;

import com.example.chat.business.dto.RoomDto;
import com.example.chat.business.entity.Room;
import com.example.chat.business.entity.RoomUser;
import com.example.chat.business.repository.RoomRepository;
import com.example.chat.business.service.RoomService;
import com.example.chat.business.service.RoomUserService;
import com.example.chat.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomUserService roomUserService;

    @Autowired
    private RoomService roomService;

    private RoomDto roomDto;

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Room findOne(Long id) {
        return roomRepository.findOne(id);
    }

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAllByNameLike(String keyword) {
        return roomRepository.findAllByNameLike(keyword);
    }

    @Override
    public List<Room> ifUserSignIn(LoginUserDetails loginUserDetails, Pageable pageable, Model model) {
        if (Objects.isNull(loginUserDetails)) {
            return roomService.findAll();
        } else {
            List<RoomUser> roomsUser = roomUserService.findAllByUserId(loginUserDetails.getUserId());
            List<Room> rooms = roomService.findAll();
            for (int i = 0; i < roomsUser.size(); i++) {
                RoomUser roomUser = roomsUser.get(i);
                Room room = roomUser.getRoom();
                rooms.add(room);
            }
            return rooms;
        }
    }
}
