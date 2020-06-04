package com.example.chat.web.controller;

import com.example.chat.business.entity.Room;
import com.example.chat.business.entity.RoomUser;
import com.example.chat.business.entity.User;
import com.example.chat.business.service.RoomService;
import com.example.chat.business.service.RoomUserService;
import com.example.chat.business.service.UserService;
import com.example.chat.web.form.RoomForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomUserService roomUserService;

    @ModelAttribute
    RoomForm setUpForm() {
        return new RoomForm();
    }

    @GetMapping("/rooms/new")
    public String newRoom(RoomForm roomForm, Model model, Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users", users);
        return "room/new";
    }

    @PostMapping("/rooms/new")
    public String createRoom(@Validated RoomForm form, BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            return newRoom(form, model, pageable);
        }

        Room room = new Room();

        BeanUtils.copyProperties(form, room);
        roomService.save(room);
        Long roomId = room.getId();

        for (int i = 0; i < form.getUserIds().size(); i++) {
            RoomUser roomUser = new RoomUser();
            BeanUtils.copyProperties(form, roomUser);
            Long userId = form.getUserIds().get(i);
            roomUserService.save(roomUser, roomId, userId);
        }
        return "redirect:/";
    }

    @GetMapping("/rooms/search")
    public String search(@RequestParam(defaultValue = "") String keyword, Model model) {
        List<Room> rooms = roomService.findAllByNameLike(keyword);
        model.addAttribute("rooms", rooms);
        return "room/search";
    }
}
