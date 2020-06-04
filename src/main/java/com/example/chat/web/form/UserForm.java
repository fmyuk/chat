package com.example.chat.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserForm implements Serializable {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;

    private String confirmPassword;

    @NotBlank
    private String nickName;

    private MultipartFile file;
}
