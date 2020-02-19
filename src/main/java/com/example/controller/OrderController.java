package com.example.controller;

import com.example.common.entity.Result;
import com.example.common.utils.ResultUtils;
import com.example.domain.OrderDTO;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/listOrder")
    public Result listUser(){
        List<OrderDTO> orderDTOList = orderService.findAll();
        return ResultUtils.success(orderDTOList);
    }

    @RequestMapping(value = "/findById")
    public Result findById(Long orderId){
        OrderDTO orderDTO = orderService.findById(orderId);
        return ResultUtils.success(orderDTO);
    }

}
