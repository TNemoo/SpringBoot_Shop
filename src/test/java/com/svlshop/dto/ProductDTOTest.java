package com.svlshop.dto;

import com.svlshop.entity.Initialization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    private final Initialization entity;

    ProductDTOTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(2L, entity.getProductDTOs().get(1).getId());
    }

    @Test
    void getTitle() {
        assertEquals(entity.getProducts().get(1).getTitle(), entity.getProductDTOs().get(1).getTitle());
    }

    @Test
    void getPrice() {
        assertEquals(entity.getProducts().get(1).getPrice().doubleValue(), entity.getProductDTOs().get(1).getPrice());
    }
}