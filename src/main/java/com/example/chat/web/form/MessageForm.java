package com.example.chat.web.form;

import lombok.Data;

@Data
public class MessageForm {

    private String body;

    private Long roomId;
}
