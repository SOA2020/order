package com.example.order.bean;

import java.util.Date;
import java.util.List;

/**
 * description: OrderInfo
 * date: 12/30/20 4:53 AM
 * author: fourwood
 */
public class OrderInfo {

    private Integer orderId;

    private Integer userId;

    private Date timeStamp;

    private Boolean sendStatus;

    private Boolean receiveStatus;

    private Integer deliveryId;

    private List<OrderCommodity> commodity;

    public OrderInfo() {
    }

    public OrderInfo(Order o, List<OrderCommodity> orderCommodityList) {
        this.orderId = o.getOrderId();
        this.userId = o.getUserId();
        this.timeStamp = o.getTimeStamp();
        this.sendStatus = o.getSendStatus();
        this.receiveStatus = o.getReceiveStatus();
        this.deliveryId = o.getDeliveryId();
        this.commodity = orderCommodityList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Boolean getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Boolean receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<OrderCommodity> getCommodity() {
        return commodity;
    }

    public void setCommodity(List<OrderCommodity> commodity) {
        this.commodity = commodity;
    }
}
