package com.svlshop.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BucketTest {

    private final Initialization entity;

    BucketTest() {
        this.entity = new Initialization();
    }

    @Test
    void getId() {
        assertEquals(8L, entity.getBucket().getId());
    }

    @Test
    void getOwner() {
        assertEquals(entity.getUser(), entity.getBucket().getOwner());
    }

    @Test
    void getProducts() {
        assertEquals(entity.getProducts(), entity.getBucket().getProducts());
    }

}