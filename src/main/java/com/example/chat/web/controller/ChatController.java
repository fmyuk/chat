package com.example.chat.web.controller;

import com.example.chat.business.entity.Message;
import com.example.chat.business.entity.Room;
import com.example.chat.business.service.MessageService;
import com.example.chat.business.service.RoomService;
import com.example.chat.business.service.RoomUserService;
import com.example.chat.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomUserService roomUserService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String index(@PageableDefault(
            page = 0,
            size = 100,
            direction = Sort.Direction.ASC,
            sort = {"createAt"}) Pageable pageable,
                        Model model,
                        @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        List<Room> rooms = roomService.ifUserSignIn(loginUserDetails, pageable, model);
        Page<Message> messages = messageService.findAll(pageable);
        model.addAttribute("rooms", rooms);
        model.addAttribute("messages", messages);
        return "chat/index";
    }

}
