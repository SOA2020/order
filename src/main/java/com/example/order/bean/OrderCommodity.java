package com.example.order.bean;

import javax.persistence.*;

/**
 * description: OrderCommodity
 * date: 12/30/20 3:59 AM
 * author: fourwood
 */
@Entity
@Table(name = "order_commodity")
@IdClass(OrderCommodityPK.class)
public class OrderCommodity {

    @Id
    @Column(name = "commodity_id")
    private Integer commodityId;

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "comment_status")
    private Boolean commentStatus;

    public OrderCommodity() {
    }

    public OrderCommodity(Integer commodityId, Integer orderId, Boolean commentStatus) {
        this.commodityId = commodityId;
        this.orderId = orderId;
        this.commentStatus = commentStatus;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    @Override
    public String toString() {
        return "OrderCommodity{" +
                "commodityId=" + commodityId +
                ", orderId=" + orderId +
                ", commentStatus=" + commentStatus +
                '}';
    }
}
