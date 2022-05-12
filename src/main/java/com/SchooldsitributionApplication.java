package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.Mapper")
//这样才会扫描filter
@ServletComponentScan
public class SchooldsitributionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchooldsitributionApplication.class, args);
    }

}
