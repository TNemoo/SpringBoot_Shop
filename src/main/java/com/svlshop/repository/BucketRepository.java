package com.svlshop.repository;

import com.svlshop.entity.Bucket;
import com.svlshop.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Bucket findByOwner(User user);
}
