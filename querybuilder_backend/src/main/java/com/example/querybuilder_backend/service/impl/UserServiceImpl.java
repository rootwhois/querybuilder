package com.example.querybuilder_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.querybuilder_backend.dao.UserDao;
import com.example.querybuilder_backend.eneity.QueryRoot;
import com.example.querybuilder_backend.eneity.User;
import com.example.querybuilder_backend.service.UserService;
import com.example.querybuilder_backend.util.QueryBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QueryBuilderUtil queryBuilderUtil;

    @Override
    public List<User> doQuery(QueryRoot root) {
        String sql = queryBuilderUtil.getSql(root.getQuery());
        System.out.println(sql);
        return userDao.doQuery(sql);
    }
}
