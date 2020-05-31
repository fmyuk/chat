package com.example.chat.business.service;

import com.example.chat.business.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User save(User user, MultipartFile file) throws IOException;

    User findOne(Long id);

    Page<User> findAll(Pageable pageable);

    byte[] downloadProfilePhoto(Long userId) throws IOException;
}
