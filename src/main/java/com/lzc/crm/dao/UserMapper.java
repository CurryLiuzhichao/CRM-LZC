package com.lzc.crm.dao;

import com.lzc.crm.base.BaseMapper;
import com.lzc.crm.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User, Integer> {
    // 根据用户名查询用户对象 queryUserByName
    User queryUserByName(String userName);

    //查询所有销售人员
    List<Map<String ,Object>> queryAllSales();

    //查询所有的客户经理
    List<Map<String, Object>> queryAllCustomerManagers();
}
