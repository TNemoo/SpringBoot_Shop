package com.svlshop.service;

import com.svlshop.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> findAll();

}
