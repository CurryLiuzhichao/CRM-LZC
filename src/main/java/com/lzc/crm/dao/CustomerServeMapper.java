package com.lzc.crm.dao;

import com.lzc.crm.base.BaseMapper;
import com.lzc.crm.vo.CustomerServe;

import java.util.List;
import java.util.Map;

public interface CustomerServeMapper extends BaseMapper {
    List<Map<String, Object>> countCustomerServeMake();
}
