package com.svlshop.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private final Initialization entity;

    CategoryTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(2L, entity.getCategories().get(1).getId());
    }

    @Test
    void getTitle() {
        assertEquals("TV", entity.getCategories().get(1).getTitle());
    }
}