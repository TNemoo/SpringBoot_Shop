package com.svlshop.security.service;

import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //security

    boolean save(User user);
    boolean save(UserDTO userDTO);
    String save(UserDTO userDTO, String principalPhoneNumber);
    boolean delete(User user);
    List<User> findAll();
    User findByPhoneNumber(String phoneNumber);
}
