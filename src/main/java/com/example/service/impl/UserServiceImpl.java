package com.example.service.impl;

import com.example.domain.UserDTO;
import com.example.domain.bo.UserBO;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(UserBO userBO) {
        return userMapper.toDto(userRepository.save(userBO));
    }

    @Override
    public UserDTO findUserByUserNameAndPassword(String userName, String password) {
        UserBO userBO = userRepository.findUserByUserNameAndPassword(userName, password);
        return userMapper.toDto(userBO);
    }

    @Override
    public UserDTO findUserByUserId(long userId) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserBO> userBOList = userRepository.findAll();
        return userMapper.toDto(userBOList);
    }


}
