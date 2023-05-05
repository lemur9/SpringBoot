package com.example.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    /**
     * 注入
     */
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void encrypt() throws Exception {
        //加密
        String username = stringEncryptor.encrypt("root");
        System.out.println("username:" + username);
        String decrypt = stringEncryptor.decrypt(username);
        System.out.println("decrypt = " + decrypt);
        String password = stringEncryptor.encrypt("123456");
        System.out.println("password:" + password);
        String decrypt1 = stringEncryptor.decrypt(password);
        System.out.println("decrypt1 = " + decrypt1);
    }

    @Test
    void contextLoads() {
    }

}
