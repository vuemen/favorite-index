package com.shun.favoriteindex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class FavoriteIndexApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void dataSourceTest() {
        System.out.println("=========================");
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println("=========================");
    }

}
