package com.lzc.crm.controller;

import com.lzc.crm.annoation.RequiredPermission;
import com.lzc.crm.base.BaseController;
import com.lzc.crm.base.ResultInfo;
import com.lzc.crm.exceptions.ParamsException;
import com.lzc.crm.model.UserModel;
import com.lzc.crm.query.UserQuery;
import com.lzc.crm.service.UserService;
import com.lzc.crm.utils.LoginUserUtil;
import com.lzc.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName ,String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        //通过try catch捕获service层异常，如果service层抛出异常，则表示登陆失败，否则登陆成功
            //调用service层的方法
            UserModel userModel = userService.userLogin(userName, userPwd);
            //设置ResultInfo的Result值 （将数据返回给请求）
            resultInfo.setResult(userModel);
//        try {
//        }catch (ParamsException p){
//            resultInfo.setCode(p.getCode());
//            resultInfo.setMsg(p.getMsg());
//            p.printStackTrace();
//        }catch (Exception e){
//            resultInfo.setCode(500);
//            resultInfo.setMsg("登陆失败！");
//        }
        return resultInfo;
    }

    /**
     * 用户修改密码
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     * @return
     */
    @RequiredPermission(code = "601003")
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request,String oldPassword,String newPassword,String repeatPassword){
        ResultInfo resultInfo = new ResultInfo();
            //获取cookie中的userId
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            //调用service层修改密码方法
            userService.updatePassWord(userId,oldPassword,newPassword,repeatPassword);
//        try {
//        }catch (ParamsException p){
//            resultInfo.setCode(p.getCode());
//            resultInfo.setMsg(p.getMsg());
//            p.printStackTrace();
//        }catch (Exception e){
//            resultInfo.setCode(500);
//            resultInfo.setMsg("修改密码失败！");
//            e.printStackTrace();
//        }
        return resultInfo;
    }



    /**
     * 进入修改密码页面
     * @return
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }



    /**
     * 查询所有的销售人员
     *
     * @return
     */
    @RequiredPermission(code = "601002")
    @RequestMapping("queryAllSales")
    @ResponseBody
    public List<Map<String,Object>> queryAllSales(){
        return userService.queryAllSales();
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * 进入用户列表页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "user/user";
    }

    /**
     * 添加用户
     *
     *
     * @param user
     * @return ResultInfo
     */
    @RequiredPermission(code = "601001")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user) {
        userService.addUser(user);
        return success("用户添加成功！");
    }

    /**
     * 更新用户
     *
     *
     * @param user
     * @return ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("用户更新成功！");
    }

    /**
     * 打开添加或修改用户的页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String add(Integer id, HttpServletRequest request){
        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            User user = userService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("userInfo",user);
        }
        return "user/add_update";
    }

    /**
     * 用户删除
     *
     * @param ids
     * @return
     */
    @RequiredPermission(code = "601004")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteByIds(ids);
        return success("用户删除成功！");
    }

    /**
     * 查询所有的客户经理
     *
     * @return
     */
    @RequestMapping("queryAllCustomerManagers")
    @ResponseBody
    public List<Map<String,Object>> queryAllCustomerManagers(){
        return userService.queryAllCustomerManagers();
    }

}
