package com.svlshop.service;

import com.svlshop.repository.BucketRepository;
import com.svlshop.repository.ProductRepository;
import com.svlshop.security.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Autowired {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Autowired(BucketRepository bucketRepository
            , ProductRepository productRepository
            , UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
