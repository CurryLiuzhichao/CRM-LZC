package com.lzc.crm.service;

import com.lzc.crm.base.BaseService;
import com.lzc.crm.dao.UserRoleMapper;
import com.lzc.crm.vo.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole,Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;

}
