package com.lzc.crm.dao;

import com.lzc.crm.base.BaseMapper;
import com.lzc.crm.vo.CustomerServe;

import java.util.List;
import java.util.Map;

public interface CustomerServeMapper extends BaseMapper {
    // 查询客户服务
    List<Map<String, Object>> countCustomerServeMake();
}
