package com.svlshop.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    private String passwordConfirm;
}
/* Здесь следует добавить роль, что бы отправлять на консоль администратору */