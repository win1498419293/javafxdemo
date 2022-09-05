package com.example.javafxdemo.Controller;

import java.util.Base64;

public class base64endode {
    public String base64(String str) {
        byte[] bytes = str.getBytes();

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Base 64 加密后：" + encoded);
        return encoded;
    }
}
