package com.lzc.crm.dao;

import com.lzc.crm.base.BaseMapper;
import com.lzc.crm.vo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    //查询所有的集合列表(只需要id与roleName）
    public List<Map<String,Object>> queryAllRoles(Integer userId);

    Role selectByRoleName(String roleName);
}