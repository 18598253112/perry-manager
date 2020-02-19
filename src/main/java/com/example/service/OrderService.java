package com.example.service;

import com.example.domain.OrderDTO;

import java.util.List;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO findById(Long orderId);
}
