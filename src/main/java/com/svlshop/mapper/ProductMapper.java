package com.svlshop.mapper;

import com.svlshop.entity.Product;
import com.svlshop.dto.ProductDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    Product toProduct(ProductDTO productDTO);
    @InheritConfiguration
    ProductDTO fromProduct(Product product);
    List<Product> toProductList(List<ProductDTO> productDTOs);
    List<ProductDTO> fromProductList(List<Product> products);
}
