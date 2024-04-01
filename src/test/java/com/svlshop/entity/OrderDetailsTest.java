package com.svlshop.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailsTest {

    private final Initialization entity;

    OrderDetailsTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(3L, entity.getOrderDetailsList().get(0).getId());
    }

    @Test
    void getOrder() {
        assertEquals(entity.getOrder(), entity.getOrderDetailsList().get(0).getOrder());
    }

    @Test
    void getProduct() {
        assertEquals(entity.getProducts().get(0), entity.getOrderDetailsList().get(0).getProduct());
    }

    @Test
    void getAmount() {
        assertEquals(new BigDecimal(3), entity.getOrderDetailsList().get(0).getAmount());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal(1252001), entity.getOrderDetailsList().get(0).getPrice());
    }
}