package com.rexxar.sharding_master_slave.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rexxar.sharding_master_slave.domain.Order;
import com.rexxar.sharding_master_slave.mapper.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void batchInsert() {
        for(int i =0;i<10;i++) {
            new Thread(() -> {
                for(int j=0;j<100;j++) {
                    List<Order> orders = new ArrayList<>();
                    for(int k = 0;k<1000;k++) {
                        Order order = new Order();
                        order.setCreateTime(new Date());
                        order.setOrderTotalAmount(new BigDecimal(String.valueOf(Math.random() * 10000)).setScale(2, RoundingMode.HALF_DOWN));
                        orders.add(order);
                    }
                    orderMapper.batchInsert(orders);
                }
            }).start();;
        }
    }

    public int queryOrderNums(){
        return orderMapper.queryOrderNums();
    }

}
