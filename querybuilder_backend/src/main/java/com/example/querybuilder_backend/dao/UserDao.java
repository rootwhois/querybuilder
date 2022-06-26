package com.example.querybuilder_backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.querybuilder_backend.eneity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    List<User> doQuery(String sql);

}
