package com.example.order.dao;

import com.example.order.bean.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * description: OrderRepository
 * date: 12/30/20 4:12 AM
 * author: fourwood
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findById(int orderId);

    List<Order> findByUserId(Integer userId);

    List<Order> findByUserIdAndSendStatus (Integer userId, Boolean sendStatus);
}
