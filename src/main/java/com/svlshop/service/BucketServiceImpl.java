package com.svlshop.service;

import com.svlshop.dto.BucketDTO;
import com.svlshop.dto.BucketDetailsDTO;
import com.svlshop.entity.Bucket;
import com.svlshop.entity.Product;
import com.svlshop.security.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{

    private final Autowired autowired;

    public BucketServiceImpl(Autowired autowired) {
        this.autowired = autowired;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, Long ... productsId) {
        Bucket bucket = new Bucket();
        bucket.setOwner(user);
        List<Long> listProducts = Arrays.asList(productsId);
        bucket.setProducts(getListProducts(listProducts));
        autowired.getBucketRepository().save(bucket);
        user.setBucket(bucket);
        autowired.getUserRepository().save(user);
        return user.getBucket();
    }

    // заполнение списка товаров ссылками вместо id
    private List<Product> getListProducts(List<Long> productsId) {
        return productsId.stream().map(autowired.getProductRepository()::getReferenceById)
                .peek(a -> {
                    if (a == null)
                        throw new UsernameNotFoundException("The product id " + a.getId() + " is not correct");
                }) .collect(Collectors.toList());
        // getReferenceById не подгружает связанные данные в отличие от findById;
    }

    @Override
    public void putInBucket(Bucket bucket, Long ... productsId) {
        if (productsId == null)
            throw new RuntimeException("Exception by method putInBucket");

        List<Product> products = bucket.getProducts();

        if (products == null)
            products = new ArrayList<>();

        for (Long x : productsId)
            products.add(autowired.getProductRepository().getReferenceById(x));

        bucket.setProducts(products);

        autowired.getBucketRepository().save(bucket);
    }

    @Override
    public Bucket findByOwnerByPhoneNumber(String phoneNumber) {
        User user = autowired.getUserRepository().findByPhoneNumber(phoneNumber);
        Bucket bucket = autowired.getBucketRepository().findByOwner(user);
        if (bucket == null)
            createBucket(user);
        autowired.getUserRepository().save(user);
        return bucket;
    }

    @Override
    @Transactional
    public void addProductToUserBucket(Long productId, String phoneNumber) {
        Bucket bucket = getUserBucket(phoneNumber);
        putInBucket(bucket, productId);
    }

    @Override
    public void removeProductFromUserBucket(Long productId, String phoneNumber) {
        Bucket bucket = getUserBucket(phoneNumber);
        List<Product> list = bucket.getProducts();
        for (Product x : list) {
            System.out.println(x);
            if (x.getId() == productId) {
                list.remove(x);
                break;
            }
        }
        autowired.getBucketRepository().save(bucket);
    }

    @Override
    public BucketDTO getBucketDTO(String phoneNumber) {
        User user = autowired.getUserRepository().findByPhoneNumber(phoneNumber);

        Bucket bucket = user.getBucket();

        if (bucket == null)
            createBucket(user);

        List<Product> list = user.getBucket().getProducts();

        BucketDTO bucketDTO = new BucketDTO();

        Map<Product, Integer> map = new HashMap<>();

        list.stream().forEach(a -> {
            if (map.containsKey(a))
                map.put(a, map.get(a) + 1);
            else
                map.put(a, 1);
        });

        list = list.stream().distinct().collect(Collectors.toList());

        System.out.println("\n***" +list + "\n");

        for (Product x : list) {
            bucketDTO.addBucketDetailsDTO(new BucketDetailsDTO(x, map.get(x)));
        }

        bucketDTO.aggregate();

        return bucketDTO;
    }

    @Override
    public void delete(Bucket bucket) {
        autowired.getBucketRepository().delete(bucket);
    }

    private User getUser(String phoneNumber) {
        User user = autowired.getUserRepository().findByPhoneNumber(phoneNumber);
        if (user == null)
            throw new UsernameNotFoundException("User not found, phone number is: " + phoneNumber);
        return user;
    }

    private Bucket getUserBucket(String phoneNumber) {
        User user = getUser(phoneNumber);
        Bucket bucket = user.getBucket();

        if (bucket == null) {
            bucket = createBucket(user);
            user.setBucket(bucket);
            autowired.getUserRepository().save(user);
        }
        return bucket;
    }
}
