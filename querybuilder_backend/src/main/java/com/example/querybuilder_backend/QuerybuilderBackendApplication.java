package com.example.querybuilder_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.querybuilder_backend.dao")
@SpringBootApplication
public class QuerybuilderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerybuilderBackendApplication.class, args);
    }

}
