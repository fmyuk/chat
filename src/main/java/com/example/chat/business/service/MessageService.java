package com.example.chat.business.service;

import com.example.chat.business.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    void save(Message message, Long userId, Long roomId);

    Page<Message> findAll(Pageable pageable);

    List<Message> findAllByRoomId(Long roomId, Pageable pageable);
}
