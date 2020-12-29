package com.example.order.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * description: OrderCommodityPK
 * date: 12/30/20 4:02 AM
 * author: fourwood
 */
public class OrderCommodityPK implements Serializable {

    private Integer commodityId;

    private Integer orderId;

    public OrderCommodityPK() {}

    public OrderCommodityPK(Integer commodityId, Integer orderId) {
        this.commodityId = commodityId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCommodityPK that = (OrderCommodityPK) o;
        return commodityId.equals(that.commodityId) && orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, orderId);
    }
}
