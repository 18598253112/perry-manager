package com.example.service;

import com.example.domain.UserDTO;
import com.example.domain.bo.UserBO;

import java.util.List;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:05
 */
public interface UserService {

    UserDTO create(UserBO userBO);

    UserDTO findUserByUserNameAndPassword(String userName, String password);

    UserDTO findUserByUserId(long userId);

    List<UserDTO> findAll();
}
