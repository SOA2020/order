package com.example.order.controllers;

import com.example.order.bean.Order;
import com.example.order.bean.OrderCommodity;
import com.example.order.bean.OrderInfo;
import com.example.order.dao.OrderCommodityRepository;
import com.example.order.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * description: OrderController
 * date: 12/30/20 4:15 AM
 * author: fourwood
 */
@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderCommodityRepository orderCommodityRepository;
    public static final int PG_SIZE = 10;

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrderById(@PathVariable int orderId){
        Order order = orderRepository.findById(orderId);
        if(order == null){
            return new ResponseEntity("Order Not Found!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(order, HttpStatus.OK);
        }
    }

//   {
//    "userId": 0,
//    "commodity":[{
//       "commentStatus":true,
//       "commodityId":0
//       }],
//    "deliveryId": 0
//    }
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody HashMap<String, Object> body){
        Integer userId = (Integer)body.getOrDefault("userId", null);
        List<HashMap<String, Object>> commodity = (List<HashMap<String, Object>>)
                body.getOrDefault("commodity", null);
        Integer deliveryId = (Integer) body.getOrDefault("deliveryId", null);
        if(userId == null || commodity == null || deliveryId == null){
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
        Order order = new Order(userId, deliveryId, false, false);
        order = orderRepository.save(order);
        ArrayList<OrderCommodity> commodityList = new ArrayList<OrderCommodity>();
        for(HashMap<String, Object> com: commodity){
            Boolean commentStatus = (Boolean) com.getOrDefault("commentStatus", null);
            Integer commodityId = (Integer) com.getOrDefault("commodityId", null);
            if(commentStatus == null || commodityId == null){
                return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
            }
            OrderCommodity orderCommodity = new OrderCommodity(commodityId, order.getOrderId(), commentStatus);
            orderCommodity = orderCommodityRepository.save(orderCommodity);
            commodityList.add(orderCommodity);
        }
        OrderInfo orderInfo = new OrderInfo(order, commodityList);
        return new ResponseEntity(orderInfo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity getOrderList(@RequestParam int userId , @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(required = false)Boolean sendStatus){
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        List<Order> orders;
        if(sendStatus == null){
             orders = orderRepository.findByUserId(userId);
        }else{
            orders = orderRepository.findByUserIdAndSendStatus(userId, sendStatus);
        }
        int sz = orders.size();
        int pgStart = (pageNumber - 1) * PG_SIZE;
        int pgEnd = pageNumber * PG_SIZE;
        pgEnd = Math.min(pgEnd, sz);
        try {
            orders = orders.subList(pgStart, pgEnd);
        }catch (IndexOutOfBoundsException | IllegalArgumentException e){
            orders.clear();
        }

        ArrayList<OrderInfo> orderInfoArrayList = new ArrayList<>();
        for(Order order : orders){
            Integer orderId = order.getOrderId();
            List<OrderCommodity> orderCommodities = orderCommodityRepository.findByOrderId(orderId);
            OrderInfo orderInfo = new OrderInfo(order, orderCommodities);
            orderInfoArrayList.add(orderInfo);
        }

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("commodities", orderInfoArrayList);
        response.put("count", sz);
        response.put("pgNum", pageNumber);
        response.put("pgSize", PG_SIZE);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/receivestatus/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity changeReceiveStatus(@PathVariable int orderId, @RequestBody HashMap<String, Boolean> body){
        Order order = orderRepository.findById(orderId);
        if(order == null){
            return new ResponseEntity<>("Order Not Found!", HttpStatus.NOT_FOUND);
        }
        Boolean status = body.getOrDefault("status", null);
        if(status == null) return new ResponseEntity<>("Bad Request!", HttpStatus.BAD_REQUEST);
        order.setReceiveStatus(status);
        orderRepository.save(order);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/sendstatus/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity changeSendStatus(@PathVariable int orderId, @RequestBody HashMap<String, Boolean> body){
        Order order = orderRepository.findById(orderId);
        if(order == null){
            return new ResponseEntity<>("Order Not Found!", HttpStatus.NOT_FOUND);
        }
        Boolean status = body.getOrDefault("status", null);
        if(status == null) return new ResponseEntity<>("Bad Request!", HttpStatus.BAD_REQUEST);
        order.setSendStatus(status);
        orderRepository.save(order);
        return new ResponseEntity(HttpStatus.OK);
    }
}
