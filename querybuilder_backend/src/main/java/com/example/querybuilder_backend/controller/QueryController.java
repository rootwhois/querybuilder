package com.example.querybuilder_backend.controller;

import com.example.querybuilder_backend.eneity.Query;
import com.example.querybuilder_backend.eneity.QueryRoot;
import com.example.querybuilder_backend.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/query")
public class QueryController {

    @Autowired
    QueryService queryService;

    @PostMapping("/getSql")
    public String getSql(@RequestBody QueryRoot root) {
        String sql = queryService.getSql(root);
        System.out.println(sql);
        return sql;
    }

}
