package com.example.chat.web.controller.advice;

import com.example.chat.security.LoginUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonControllerAdvice {

    @ModelAttribute(name = "loginUser")
    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        return loginUserDetails;
    }
}
