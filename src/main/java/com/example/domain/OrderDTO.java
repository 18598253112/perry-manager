package com.example.domain;

import com.example.annotation.NeedSetValue;
import com.example.service.impl.UserServiceImpl;
import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Data
public class OrderDTO implements Serializable {

    private Long id;

    private Long customerId;

    @NeedSetValue(beanClass = UserServiceImpl.class, param = "customerId", method = "findUserByUserId", targetFiled = "userName", targetClass = UserDTO.class)
    private String customerName;

}
