package com.example.order.dao;

import com.example.order.bean.Order;
import com.example.order.bean.OrderCommodity;
import com.example.order.bean.OrderCommodityPK;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * description: OrderCommodityRepository
 * date: 12/30/20 4:13 AM
 * author: fourwood
 */
public interface OrderCommodityRepository extends CrudRepository<OrderCommodity, OrderCommodityPK> {
    List<OrderCommodity> findByOrderId(Integer orderId);
}
