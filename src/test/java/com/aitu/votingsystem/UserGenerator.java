package com.aitu.votingsystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "asd";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
