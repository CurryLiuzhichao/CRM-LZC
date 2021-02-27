package com.lzc.crm.query;

import com.lzc.crm.base.BaseQuery;

public class OrderDetailsQuery extends BaseQuery {
    private Integer orderId; // 订单ID

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
