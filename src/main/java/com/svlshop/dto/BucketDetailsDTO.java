package com.svlshop.dto;

import com.svlshop.entity.Product;
import com.svlshop.service.General;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailsDTO { //информация о группе одного товара в корзине //кратковременный класс для удобства
    private String title;
    private long productId;
    private double price;

    private int quantity;
    private double sum;

    public BucketDetailsDTO(Product product, Integer quantity) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice().doubleValue();
        this.quantity = quantity;
        this.sum = General.doubleRounding(price * quantity,2);
    }
}

