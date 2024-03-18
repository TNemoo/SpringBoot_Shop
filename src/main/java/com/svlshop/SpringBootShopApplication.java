package com.svlshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShopApplication.class, args);
    }

}
