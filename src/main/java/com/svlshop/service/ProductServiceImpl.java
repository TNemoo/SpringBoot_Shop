package com.svlshop.service;

import com.svlshop.dto.ProductDTO;
import com.svlshop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final Autowired autowired;

    public ProductServiceImpl(BucketService bucketService, Autowired autowired, Autowired service) {
        this.autowired = service;
    }

    @Override
    public List<ProductDTO> findAll() {
        return mapper.fromProductList(autowired.getProductRepository().findAll());
    }

}
