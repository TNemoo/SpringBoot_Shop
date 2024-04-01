package com.svlshop.entity;

import com.svlshop.enums.Order_status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private final Initialization entity;

    OrderTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(15L, entity.getOrder().getId());
    }

    @Test
    void getShopAddress() {
        assertEquals("New York, First street", entity.getOrder().getShopAddress());
    }

    @Test
    void getOwner() {
        assertEquals(entity.getUser(), entity.getOrder().getOwner());
    }

    @Test
    void getDetails() {
        assertEquals(entity.getOrderDetailsList(), entity.getOrder().getDetails());
    }

    @Test
    void getDateCreated() {
        assertEquals(entity.getDateCreated(), entity.getOrder().getDateCreated());
    }

    @Test
    void getDateUpdated() {
        assertEquals(entity.getDateUpdated(), entity.getOrder().getDateUpdated());
    }

    @Test
    void getStatus() {
        assertEquals(Order_status.NEW, entity.getOrder().getStatus());
    }
}