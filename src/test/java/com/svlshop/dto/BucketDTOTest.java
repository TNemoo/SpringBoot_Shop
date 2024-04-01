package com.svlshop.dto;

import com.svlshop.entity.Initialization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketDTOTest {

    private final Initialization entity;

    BucketDTOTest() {
        this.entity = new Initialization();
    }

    @Test
    void getQuantity() {
        assertEquals(3+5+1, entity.getBucketDTO().getQuantity());
    }

    @Test
    void getTotal() {
        assertEquals(3*1252001.0+5*1252002.0+1*1252003.0, entity.getBucketDTO().getTotal());
    }

    @Test
    void getBucketDetailsDTOS() {
        assertEquals(entity.getBucketDetailsDTOs().get(1), entity.getBucketDTO().getBucketDetailsDTOS().get(1));
    }
}