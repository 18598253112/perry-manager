package com.example.service.impl;

import com.example.annotation.NeedSetFiledValue;
import com.example.annotation.NeedSetFiledValueSingle;
import com.example.domain.OrderDTO;
import com.example.domain.bo.OrderBO;
import com.example.mapper.OrderMapper;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderRepository orderRepository;

    @Override
    @NeedSetFiledValue
    public List<OrderDTO> findAll() {
        List<OrderBO> orderBOList = orderRepository.findAll();
        List<OrderDTO> list = orderMapper.toDto(orderBOList);
        return list;
    }

    @Override
    @NeedSetFiledValueSingle
    public OrderDTO findById(Long orderId) {
        Optional<OrderBO> repository = orderRepository.findById(orderId);
        OrderBO orderBO = repository.orElse(null);
        return orderMapper.toDto(orderBO);
    }

}
