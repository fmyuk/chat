package com.example.chat.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocketMessage {

    private String body;

    private String username;

    private String createdAt;

    private Long userId;
}
