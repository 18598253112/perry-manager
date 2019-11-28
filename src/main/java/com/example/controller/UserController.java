package com.example.controller;

import com.example.common.entity.Result;
import com.example.common.utils.ResultUtils;
import com.example.domain.UserDTO;
import com.example.domain.bo.UserBO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:01
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public Result create(@Validated @RequestBody UserBO userBO){
        userService.create(userBO);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/listUser")
    public Result listUser(){
        List<UserDTO> userDTOList = userService.findAll();
        return ResultUtils.success(userDTOList);
    }

}
