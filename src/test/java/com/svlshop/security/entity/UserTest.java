package com.svlshop.security.entity;

import com.svlshop.entity.Initialization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private final Initialization entity;

    UserTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(10L, entity.getUser().getId());
    }

    @Test
    void getName() {
        assertEquals("John", entity.getUser().getName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("10-265-215-65-98", entity.getUser().getPhoneNumber());
    }

    @Test
    void getEmail() {
        assertEquals("John@gmail.com", entity.getUser().getEmail());
    }

    @Test
    void getAddress() {
        assertEquals("New York", entity.getUser().getAddress());
    }

    @Test
    void getPassword() {
        assertEquals("XX-dkk-rhjjdls", entity.getUser().getPassword());
    }

    @Test
    void getRole() {
        assertEquals(Role.USER, entity.getUser().getRole());
    }

    @Test
    void getBucket() {
        assertEquals(entity.getBucket(), entity.getUser().getBucket());
    }
}

