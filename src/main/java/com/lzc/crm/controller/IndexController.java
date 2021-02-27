package com.lzc.crm.controller;

import com.lzc.crm.base.BaseController;
import com.lzc.crm.service.PermissionService;
import com.lzc.crm.service.UserService;
import com.lzc.crm.utils.LoginUserUtil;
import com.lzc.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        //获取cookid中的用户id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户对象,设置session作用域
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        // 通过当前登录用户ID查询当前登录用户拥有的资源列表 （查询对应资源的授权码）
        List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        // 将集合设置到session作用域中
        request.getSession().setAttribute("permissions", permissions);

        return "main";
    }


}
