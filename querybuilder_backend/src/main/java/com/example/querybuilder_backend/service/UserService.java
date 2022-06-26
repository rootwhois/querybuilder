package com.example.querybuilder_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.querybuilder_backend.eneity.QueryRoot;
import com.example.querybuilder_backend.eneity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> doQuery(QueryRoot root);

}
