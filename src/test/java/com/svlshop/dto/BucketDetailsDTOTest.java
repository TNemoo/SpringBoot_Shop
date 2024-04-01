package com.svlshop.dto;

import com.svlshop.entity.Initialization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BucketDetailsDTOTest {

    private final Initialization entity;

    BucketDetailsDTOTest() {
        this.entity = new Initialization();
    }

    @Test
    void getTitle() {
        assertEquals(entity.getProducts().get(1).getTitle(), entity.getBucketDetailsDTOs().get(1).getTitle());
    }

    @Test
    void getProductId() {
        assertEquals(entity.getProducts().get(1).getId(), entity.getBucketDetailsDTOs().get(1).getProductId());
    }

    @Test
    void getPrice() {
        assertEquals(1252002.0, entity.getBucketDetailsDTOs().get(1).getPrice());
    }

    @Test
    void getQuantity() {
        assertEquals(5, entity.getBucketDetailsDTOs().get(1).getQuantity());
    }

    @Test
    void getSum() {
        assertEquals(5*1252002.0, entity.getBucketDetailsDTOs().get(1).getSum());
    }
}