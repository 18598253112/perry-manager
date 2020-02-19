package com.example.repository;

import com.example.domain.bo.OrderBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
public interface OrderRepository extends JpaRepository<OrderBO, Long>, JpaSpecificationExecutor {
}
