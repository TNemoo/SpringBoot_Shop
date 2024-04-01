package com.svlshop.security.service;

import com.svlshop.entity.Initialization;
import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.entity.Role;
import com.svlshop.security.entity.User;
import com.svlshop.security.repository.UserRepository;
import com.svlshop.service.BucketService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private BucketService bucketService;
    private final Initialization initialization;

    UserServiceImplTest() {
        this.initialization = new Initialization();
    }

    @BeforeAll

    static void BeforeAll() {
        System.out.println("test's starting");
    }

    @AfterAll
    static void AfterAll() {
        System.out.println("test's done");
    }

    @BeforeEach
    void setUp() {
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);
        bucketService = Mockito.mock(BucketService.class);
        userService = new UserServiceImpl(userRepository, bucketService, passwordEncoder);
    }

    @Test
    void save_throwsException1() {
        //Проверка на выбрасывание исключения при несовпадении пароля и подтверждения пароля:
        User user = initialization.getUser();
        UserDTO userDTO = new UserDTO(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getAddress()
                , user.getPassword(), "");

        assertThrows(RuntimeException.class, () -> userService.save(userDTO));
    }

    @Test
    void save_throwsException2() {
        //Проверка на выбрасывание исключения при наличии в БД номера телефона введенного при регистрации новым пользователем:
        UserDTO userDTO = initialization.getUserDTO();
        Mockito.when(userRepository.findByPhoneNumber(userDTO.getPhoneNumber())).getMock(); //для findByPhoneNumber()

        assertThrows(RuntimeException.class, () -> userService.save(userDTO));
    }

    @Test
    void loadUserByUsername() {
        String phoneNumber = "10-232-256-98-96";
        User user = User.builder().id(1L).name("John").phoneNumber(phoneNumber).role(Role.USER).password("fshshsv5y45362c2c").build();

        Mockito.when(userRepository.findByPhoneNumber(Mockito.eq(phoneNumber))).thenReturn(user);

        assertNotNull(userService.loadUserByUsername(phoneNumber));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(phoneNumber));

        UserDetails ud = new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(), roles);
        assertEquals(ud, userService.loadUserByUsername(phoneNumber));
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>();
        users.add(initialization.getUser());

        Mockito.when(userRepository.findAll()).thenReturn(users);

        assertNotNull(userService.findAll());
        assertEquals(users, userService.findAll());
    }

    @Test
    void findByPhoneNumber() {
        String phoneNumber = "10-232-256-98-96";
        User user = User.builder().id(1L).name("John").phoneNumber(phoneNumber).build();

        Mockito.when(userRepository.findByPhoneNumber(Mockito.eq(phoneNumber))).thenReturn(user);

        assertNotNull(userService.findByPhoneNumber(phoneNumber));
        assertEquals(user, userService.findByPhoneNumber(phoneNumber));

        //проверка на выбрасывание исключения, если такой номер отсутствует в базе
        assertThrows(RuntimeException.class, () -> userService.findByPhoneNumber(Mockito.anyString()));
    }
}