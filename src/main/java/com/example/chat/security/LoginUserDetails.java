package com.example.chat.security;


import com.example.chat.business.entity.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private Long userId;
    private String userNickName;

    public LoginUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userId = user.getId();
        this.userNickName = user.getNickname();
    }
}
