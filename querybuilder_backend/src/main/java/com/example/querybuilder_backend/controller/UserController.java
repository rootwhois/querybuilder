package com.example.querybuilder_backend.controller;

import com.example.querybuilder_backend.eneity.QueryRoot;
import com.example.querybuilder_backend.eneity.User;
import com.example.querybuilder_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/doQuery")
    public List<User> doQuery(@RequestBody QueryRoot root) {
        return userService.doQuery(root);
    }

}
