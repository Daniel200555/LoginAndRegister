package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
    private String nickname;
    private String email;
    private String phone;
    private String password;
}
