package com.example.order.bean;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * description: Order
 * date: 12/30/20 3:39 AM
 * author: fourwood
 */
@Entity
@Table(name = "order_t")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "time_stamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date timeStamp;

    @Column(name = "send_status")
    private Boolean sendStatus;

    @Column(name = "receive_status")
    private Boolean receiveStatus;

    @Column(name = "delivery_id")
    private Integer deliveryId;

    public Order(Integer userId, Integer deliveryId, Boolean sendStatus, Boolean receiveStatus) {
        this.userId = userId;
        this.sendStatus = sendStatus;
        this.receiveStatus = receiveStatus;
        this.deliveryId = deliveryId;
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

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", timeStamp=" + timeStamp +
                ", sendStatus=" + sendStatus +
                ", receiveStatus=" + receiveStatus +
                ", deliveryId=" + deliveryId +
                '}';
    }

}
