package com.rexxar.sharding_master_slave.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private long orderId;
    private BigDecimal orderTotalAmount;
    private Date createTime;
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public BigDecimal getOrderTotalAmount() {
        return orderTotalAmount;
    }
    public void setOrderTotalAmount(BigDecimal d) {
        this.orderTotalAmount = d;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
