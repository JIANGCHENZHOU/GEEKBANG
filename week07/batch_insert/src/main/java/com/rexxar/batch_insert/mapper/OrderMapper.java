package com.rexxar.batch_insert.mapper;

import java.util.List;

import com.rexxar.batch_insert.configuration.datasource.DynaminDataSource;
import com.rexxar.batch_insert.domain.Order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
@DynaminDataSource//对mapper做切面，根据类内方法名是否含有query选择mysql数据源的节点（master/slave）
public interface OrderMapper {

    @Insert("<script>" + 
            "insert into geek_db.order (order_total_amount, create_time) " +
            "values" +
            "<foreach collection='orders' item='item' separator=',' > " +
            " (#{item.orderTotalAmount}, #{item.createTime})\n" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "orderId",keyColumn = "order_id")
    int batchInsert(@Param(value = "orders") List<Order> orders);

    @Select("select count(order_id) from geek_db.order")
    int queryOrderNums();
}
