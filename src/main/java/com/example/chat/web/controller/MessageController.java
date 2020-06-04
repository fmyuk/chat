package com.example.chat.web.controller;

import com.example.chat.business.dto.RoomDto;
import com.example.chat.business.entity.Message;
import com.example.chat.business.entity.Room;
import com.example.chat.business.entity.SocketMessage;
import com.example.chat.business.service.MessageService;
import com.example.chat.business.service.RoomService;
import com.example.chat.business.service.RoomUserService;
import com.example.chat.business.service.UserService;
import com.example.chat.security.LoginUserDetails;
import com.example.chat.web.form.MessageForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomUserService roomUserService;

    @ModelAttribute
    MessageForm setUpForm() {
        return new MessageForm();
    }

    @GetMapping("/rooms/{roomId}/messages")
    public String messageIndex(@PageableDefault(
            size = 100,
            direction = Sort.Direction.ASC,
            sort = {"createAt"}) Pageable pageable,
                               Model model,
                               @PathVariable Long roomId,
                               @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        Room room = roomService.findOne(roomId);
        List<Room> rooms = roomService.ifUserSignIn(loginUserDetails, pageable, model);
        List<Message> messages = messageService.findAllByRoomId(roomId, pageable);
        model.addAttribute("room", room);
        model.addAttribute("rooms", rooms);
        model.addAttribute("messages", messages);
        return "chat/index";
    }

    @MessageMapping("/{roomId}/messages/post")
    @SendTo("/rooms/{roomId}/messages")
    public SocketMessage createMessage(@PathVariable RoomDto roomMessage,
                                       @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        Message message = new Message();
        BeanUtils.copyProperties(roomMessage, message);
        messageService.save(message, loginUserDetails.getUserId(), roomMessage.getRoomId());
        return new SocketMessage(message.getBody(), loginUserDetails.getUserNickName(),
                message.getCreatedAt().toString(), loginUserDetails.getUserId());
    }
}
