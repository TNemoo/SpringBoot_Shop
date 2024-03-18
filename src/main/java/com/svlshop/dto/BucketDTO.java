package com.svlshop.dto;

import com.svlshop.service.General;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO { //информация о всех группах товаров //кратковременный класс для удобства
    private int quantity;
    private double total;
    private List<BucketDetailsDTO> bucketDetailsDTOS = new ArrayList<>();

    public void aggregate() {
        quantity = (int) bucketDetailsDTOS.stream().mapToInt(BucketDetailsDTO::getQuantity).sum();
        total = bucketDetailsDTOS.stream().mapToDouble(a -> a.getPrice() * a.getQuantity()).sum();
        total = General.doubleRounding(total,2);
    }

    public void addBucketDetailsDTO(BucketDetailsDTO bucketDetailsDTO) {
        bucketDetailsDTOS.add(bucketDetailsDTO);
    }
}
