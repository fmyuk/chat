package com.example.chat.web.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RoomForm {
    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    private List<Long> userIds;
}
