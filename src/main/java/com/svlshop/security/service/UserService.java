package com.svlshop.security.service;

import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //security

    boolean save(UserDTO userDTO);
    boolean save(User user);
    boolean delete(User user);
    boolean save(UserDTO userDTO, String principalPhoneNumber);
    List<User> findAll();
    User findByPhoneNumber(String phoneNumber);
}
