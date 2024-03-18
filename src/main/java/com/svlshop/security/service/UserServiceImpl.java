package com.svlshop.security.service;

import com.svlshop.security.repository.UserRepository;
import com.svlshop.security.entity.Role;
import com.svlshop.security.entity.User;
import com.svlshop.security.dto.UserDTO;
import com.svlshop.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BucketService bucketService;
    private final PasswordEncoder passwordEncoder;

    //    @Lazy
    public UserServiceImpl(UserRepository userRepository, BucketService bucketService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bucketService = bucketService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override //сохранение нового пользователя
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getPasswordConfirm())) //сравнение с подтверждением пароля
            throw new RuntimeException("Passwords don't match");

        checkExistPhoneNumber(userDTO.getPhoneNumber());
        User user = userDTOToUser(userDTO);

//        userRepository.save(user);
        bucketService.createBucket(user);
        return true;
    }

    private User userDTOToUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .build();
    }

    @Override //изменение данных пользователя
    public String save(UserDTO userDTO, String principalPhoneNumber) {
        User user = userRepository.findByPhoneNumber(principalPhoneNumber);

        if (!userDTO.getPhoneNumber().equals(principalPhoneNumber))
            checkExistPhoneNumber(userDTO.getPhoneNumber());

        //проверка пароля, сравнение введенного текущего пароля с БД
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new RuntimeException("The currently password is not correct");

        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());

        String s = "Personal data has been changed";

        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getPasswordConfirm());

        if (!(userDTO.getPasswordConfirm().isEmpty() || userDTO.getPasswordConfirm() == null)) {
            user.setPassword(passwordEncoder.encode(userDTO.getPasswordConfirm()));
            s = "Password and personal data have been changed";
        }

        userRepository.save(user);
        return s;
    }

    @Override
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    private boolean checkExistPhoneNumber(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber) != null) {
            throw new RuntimeException("The phone number " + phoneNumber + " is already exist");
        }
        return true;
    }



    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);

        if (user == null) {
            System.out.println("User with phone number" + phoneNumber + " not found. Check the phone number, please");
            throw new UsernameNotFoundException("User with phone number" + phoneNumber
                    + " not found. Check the phone number, please");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                roles
        );
    }


    @Override
    public List<User> findAll() {
        List<User> userShort = userRepository.findAll();

        for (User x : userShort) {
            x.setId(0L);
            x.setPassword("");
            x.setBucket(null);
        }
        return userShort;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null)
            throw new UsernameNotFoundException("User's number " + phoneNumber + " was not found");
        return user;
    }

//    private UserDTO userToDTO(User user) {
//        return UserDTO.builder()
//                .name(user.getName())
//                .phoneNumber(user.getPhoneNumber())
//                .email(user.getEmail())
//                .address(user.getAddress())
//                .build();
//    }

}
