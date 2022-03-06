package com.rexxar.sharding_master_slave.controller;

import com.rexxar.sharding_master_slave.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class BatchInsertOrderAction {
    
    Logger logger = LoggerFactory.getLogger(BatchInsertOrderAction.class);

    @Autowired
    OrderService orderService;

    //批量插入100w数据（使用mysql-master节点插入）
    @GetMapping(value = "/add")
    public void batchInsert() {
        long controllerStart = System.currentTimeMillis();
        logger.info("1.======================>" + controllerStart);
        orderService.batchInsert();
        long controllerEnd = System.currentTimeMillis();
        logger.info("2.======================>" + controllerEnd);
        logger.info("=========================>" + (controllerEnd - controllerStart));
    }

    //查询订单记录数（使用mysql-slave节点查询）
    @GetMapping(value = "/nums")
    public void nums() {
        int nums = orderService.queryOrderNums();
        logger.info("nums = " + nums);
    }
}
