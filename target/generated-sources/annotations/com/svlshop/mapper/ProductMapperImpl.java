package com.svlshop.mapper;

import com.svlshop.dto.ProductDTO;
import com.svlshop.entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-30T15:53:51+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.getId() );
        product.title( productDTO.getTitle() );
        product.price( BigDecimal.valueOf( productDTO.getPrice() ) );

        return product.build();
    }

    @Override
    public ProductDTO fromProduct(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( product.getId() );
        productDTO.title( product.getTitle() );
        if ( product.getPrice() != null ) {
            productDTO.price( product.getPrice().doubleValue() );
        }

        return productDTO.build();
    }

    @Override
    public List<Product> toProductList(List<ProductDTO> productDTOs) {
        if ( productDTOs == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDTOs.size() );
        for ( ProductDTO productDTO : productDTOs ) {
            list.add( toProduct( productDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductDTO> fromProductList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( fromProduct( product ) );
        }

        return list;
    }
}
