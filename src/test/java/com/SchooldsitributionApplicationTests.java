package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchooldsitributionApplicationTests {

    @Test
    void contextLoads() {
        String imageurl = "59654eb6-7372-41a2-9bfd-4267c57c7d57.jpg";

        System.out.println(imageurl.lastIndexOf("."));
        System.out.println(imageurl.substring(imageurl.lastIndexOf(".")));





    }

}
