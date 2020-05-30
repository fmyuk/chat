package com.example.chat.business.dto;

import lombok.Data;

@Data
public class RoomDto {

    private String body;
    private Long roomId;

    @Override
    public String toString() {
        return "RoomDto{" +
                "body=" + body + "\"" +
                ", roomId=" + roomId +
                "}";
    }
}
