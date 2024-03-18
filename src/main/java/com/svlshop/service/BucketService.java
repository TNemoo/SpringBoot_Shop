package com.svlshop.service;

import com.svlshop.dto.BucketDTO;
import com.svlshop.entity.Bucket;
import com.svlshop.security.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface BucketService {
    Bucket createBucket(User user, Long ... productsIdCount);
    void putInBucket(Bucket bucket, Long ... productsIdCount);
    Bucket findByOwnerByPhoneNumber(String phoneNumber);
    void addProductToUserBucket(Long productId, String phoneNumber);
    void removeProductFromUserBucket(Long productId, String phoneNumber);
    BucketDTO getBucketDTO(String phoneNumber);
    void delete(Bucket bucket);
}
