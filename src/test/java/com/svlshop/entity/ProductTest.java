package com.svlshop.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private final Initialization entity;

    ProductTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(2L, entity.getProducts().get(1).getId());
    }

    @Test
    void getTitle() {
        assertEquals("USR Robot Model 2", entity.getProducts().get(1).getTitle());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal(1252002), entity.getProducts().get(1).getPrice());
    }

    @Test
    void getReducedPrice() {
        assertEquals(new BigDecimal(1290002), entity.getProducts().get(1).getReducedPrice());
    }

    @Test
    void getCategories() {
        assertEquals(entity.getCategories(), entity.getProducts().get(1).getCategories());
    }
}