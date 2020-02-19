package com.example.mapper;

import com.example.domain.OrderDTO;
import com.example.domain.bo.OrderBO;
import org.mapstruct.Mapper;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, OrderBO>{
}
