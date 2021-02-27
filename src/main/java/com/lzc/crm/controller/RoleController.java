package com.lzc.crm.controller;

import com.lzc.crm.annoation.RequiredPermission;
import com.lzc.crm.base.BaseController;
import com.lzc.crm.base.ResultInfo;
import com.lzc.crm.query.RoleQuery;
import com.lzc.crm.service.RoleService;
import com.lzc.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("role")
@Controller
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;


    /**
     * 查询所有的角色列表
     *
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    /**
     * 分页条件查询角色列表
     *
     *
     * @param roleQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequiredPermission(code = "602002")
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery roleQuery) {
        return roleService.queryByParamsForTable(roleQuery);
    }

    /**
     * 进入角色管理页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }

    /**
     * 添加角色
     *
     *
     * @param role
     * @return com.xxxx.crm.base.ResultInfo
     */
    @RequiredPermission(code = "602001")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role) {
        roleService.addRole(role);
        return success("角色添加成功！");
    }

    /***
     * 进入添加/更新角色信息的页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer roleId, HttpServletRequest request) {
        // 如果roleId不为空，则表示修改操作，通过角色ID查询角色记录，存到请求域中
        if (roleId != null) {
            // 通过角色ID查询角色记录
            Role role = roleService.selectByPrimaryKey(roleId);
            // 设置到请求域中
            request.setAttribute("role", role);
        }
        return "role/add_update";
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequiredPermission(code = "602003")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Role role){
        roleService.updateRole(role);
        return success("角色修改成功！");
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequiredPermission(code = "602004")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer roleId) {
        roleService.deleteRole(roleId);
        return success("角色删除成功！");
    }

    /**
     * 角色授权
     *
     * @param roleId
     * @param mIds
     * @return
     */
    @PostMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer roleId, Integer[] mIds) {

        roleService.addGrant(roleId, mIds);

        return success("角色授权成功！");
    }


}
