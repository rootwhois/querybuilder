package com.example.querybuilder_backend.service;

import com.example.querybuilder_backend.eneity.QueryRoot;
import com.example.querybuilder_backend.util.QueryBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    @Autowired
    private QueryBuilderUtil queryBuilderUtil;

    public String getSql(QueryRoot root) {
        return queryBuilderUtil.getSql(root.getQuery());
    }

}
