package com.svlshop.entity;

import com.svlshop.dto.BucketDTO;
import com.svlshop.dto.BucketDetailsDTO;
import com.svlshop.dto.ProductDTO;
import com.svlshop.enums.Order_status;
import com.svlshop.security.dto.UserDTO;
import com.svlshop.security.entity.Role;
import com.svlshop.security.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Initialization {

    private final User user;
    private final Bucket bucket;
    private final List<Product> products;
    private final List<Category> categories;
    private final Order order;
    private final List<OrderDetails> orderDetailsList;
    private final LocalDateTime dateCreated;
    private final LocalDateTime dateUpdated;
    private final List<BucketDetailsDTO> bucketDetailsDTOs;
    private final BucketDTO bucketDTO;
    private final List<ProductDTO> productDTOs;
    private final UserDTO userDTO;

    public Initialization() {

        /** Entity */
        this.user = new User(10L, "John", "10-265-215-65-98"
                , "John@gmail.com", "New York", "XX-dkk-rhjjdls", Role.USER, null, false);

        this.categories = new ArrayList<>();
        categories.add(new Category(1L, "USR Robots"));
        categories.add(new Category(2L, "TV"));
        categories.add(new Category(3L, "PC"));

        Product product1 = new Product(1L, "USR Robot Model 1", new BigDecimal(1252001)
                , new BigDecimal(1290001), true, categories);
        Product product2 = new Product(2L, "USR Robot Model 2", new BigDecimal(1252002)
                        , new BigDecimal(1290002), true, categories);
        Product product3 = new Product(3L, "USR Robot Model 3", new BigDecimal(1252003)
                        , new BigDecimal(1290003), true, categories);

        this.products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        this.bucket = new Bucket(8L, user, products);

        user.setBucket(bucket);

        OrderDetails orderDetails1 = new OrderDetails(3L, null, product1, new BigDecimal(3), product1.getPrice());
        OrderDetails orderDetails2 = new OrderDetails(4L, null, product2, new BigDecimal(5), product2.getPrice());
        OrderDetails orderDetails3 = new OrderDetails(5L, null, product3, new BigDecimal(1), product3.getPrice());

        this.orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails1);
        orderDetailsList.add(orderDetails2);
        orderDetailsList.add(orderDetails3);

        this.dateCreated = LocalDateTime.of(2014, Month.APRIL, 8, 12, 3);
        this.dateUpdated = LocalDateTime.of(2014, Month.APRIL, 8, 14, 35);

        this.order = new Order(15L, "New York, First street", user, orderDetailsList
                , dateCreated, dateUpdated, Order_status.NEW);

        orderDetails1.setOrder(order);
        orderDetails2.setOrder(order);
        orderDetails3.setOrder(order);

        /** DTO */
        BucketDetailsDTO bucketDetailsDTO1 = new BucketDetailsDTO(product1, 3);
        BucketDetailsDTO bucketDetailsDTO2 = new BucketDetailsDTO(product2, 5);
        BucketDetailsDTO bucketDetailsDTO3 = new BucketDetailsDTO(product3, 1);
        bucketDetailsDTOs = new ArrayList<>();
        bucketDetailsDTOs.add(bucketDetailsDTO1);
        bucketDetailsDTOs.add(bucketDetailsDTO2);
        bucketDetailsDTOs.add(bucketDetailsDTO3);

        bucketDTO = new BucketDTO();
        bucketDTO.addBucketDetailsDTO(bucketDetailsDTO1);
        bucketDTO.addBucketDetailsDTO(bucketDetailsDTO2);
        bucketDTO.addBucketDetailsDTO(bucketDetailsDTO3);
        bucketDTO.aggregate();

        productDTOs = new ArrayList<>();
        productDTOs.add(new ProductDTO(product1.getId(), product1.getTitle(), product1.getPrice().doubleValue()));
        productDTOs.add(new ProductDTO(product2.getId(), product2.getTitle(), product2.getPrice().doubleValue()));
        productDTOs.add(new ProductDTO(product3.getId(), product3.getTitle(), product3.getPrice().doubleValue()));

        userDTO = new UserDTO(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getAddress()
                , user.getPassword(), user.getPassword());
    }

    public User getUser() {
        return user;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Order getOrder() {
        return order;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public List<BucketDetailsDTO> getBucketDetailsDTOs() {
        return bucketDetailsDTOs;
    }

    public BucketDTO getBucketDTO() {
        return bucketDTO;
    }

    public List<ProductDTO> getProductDTOs() {
        return productDTOs;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
