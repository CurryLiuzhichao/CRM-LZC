package com.lzc.crm.service;

import com.lzc.crm.base.BaseService;
import com.lzc.crm.dao.PermissionMapper;
import com.lzc.crm.vo.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {
    @Resource
    private PermissionMapper permissionMapper;


    /***
     * 通过查询用户拥有的角色，角色拥有的资源，得到用户拥有的资源列表 （资源权限码）
     *
     * @param userId
     * @return java.util.List<java.lang.String>
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<String> queryUserHasRoleHasPermissionByUserId(Integer userId) {
        return permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
    }
}
