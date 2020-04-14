package com.shun.favoriteindex;

import com.shun.favoriteindex.util.encrypt.FiEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoriteIndexApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void encrypt() {
        System.out.println(FiEncrypt.encrypt("123456"));
    }

    @Test
    void decrypt() {
        System.out.println(FiEncrypt.decrypt("3P97uUVDU9g="));
    }

}
