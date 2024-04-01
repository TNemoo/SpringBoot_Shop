package com.svlshop.security.dto;

import com.svlshop.entity.Initialization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private final Initialization entity;

    UserDTOTest() {
        this.entity = new Initialization();
    }

    @Test
    void getName() {
        assertEquals(entity.getUser().getName(), entity.getUserDTO().getName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(entity.getUser().getPhoneNumber(), entity.getUserDTO().getPhoneNumber());
    }

    @Test
    void getEmail() {
        assertEquals(entity.getUser().getEmail(), entity.getUserDTO().getEmail());
    }

    @Test
    void getAddress() {
        assertEquals(entity.getUser().getAddress(), entity.getUserDTO().getAddress());
    }

    @Test
    void getPassword() {
        assertEquals(entity.getUser().getPassword(), entity.getUserDTO().getPassword());
    }

    @Test
    void getPasswordConfirm() {
        assertEquals(entity.getUser().getPassword(), entity.getUserDTO().getPasswordConfirm());
    }
}