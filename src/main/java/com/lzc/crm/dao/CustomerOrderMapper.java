package com.lzc.crm.dao;

import com.lzc.crm.base.BaseMapper;
import com.lzc.crm.vo.CustomerOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<CustomerOrder,Integer> {

    // 通过订单ID查询订单记录
    Map<String ,Object> queryOrderById(Integer orderId);

    // 通过客户ID查询最后一条订单记录
    CustomerOrder queryLossCustomerOrderByCustomerId(Integer customerId);
}