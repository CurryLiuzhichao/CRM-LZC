package com.lzc.crm.controller;

import com.lzc.crm.annoation.RequiredPermission;
import com.lzc.crm.base.BaseController;
import com.lzc.crm.base.ResultInfo;
import com.lzc.crm.dao.SaleChanceMapper;
import com.lzc.crm.enums.StateStatus;
import com.lzc.crm.query.SaleChanceQuery;
import com.lzc.crm.service.CusDevPlanService;
import com.lzc.crm.service.SaleChanceService;
import com.lzc.crm.utils.AssertUtil;
import com.lzc.crm.utils.CookieUtil;
import com.lzc.crm.utils.LoginUserUtil;
import com.lzc.crm.vo.CusDevPlan;
import com.lzc.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;


    /**
     * 营销机会数据查询（分页多条件查询）
     * 如果flag的值不为空，且值为1，则表示当前查询的是客户开发计划；否则查询营销机会数据
     *
     * @param saleChanceQuery
     * @return
     */
    @RequiredPermission(code = "101001")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery, Integer flag,HttpServletRequest request){
        // 判断flag的值
        if (flag != null && flag == 1) {
            // 查询客户开发计划
            // 设置分配状态
            saleChanceQuery.setState(StateStatus.STATED.getType());
            // 设置指派人（当前登录用户的ID）
            // 从cookie中获取当前登录用户的ID
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            saleChanceQuery.setAssignMan(userId);
        }

        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }

    /**
     * 进入营销机会管理页面
     * @return
     */
    @RequiredPermission(code = "1010")
    @RequestMapping("index")
    public String index(){
        return "saleChance/sale_chance";
    }

    /**
     * 添加营销机会
     *
     * @param saleChance
     * @param request
     * @return
     */
    @RequiredPermission(code = "101002")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addSaleChance(SaleChance saleChance, HttpServletRequest request){
        //从cookie中获取当前登录的用户名
        String userName = CookieUtil.getCookieValue(request, "userName");
        //设置用户名到营销机会对象
        saleChance.setCreateMan(userName);
        //调用Service层的添加方法
        saleChanceService.addSaleChance(saleChance);
        return success("营销机会数据添加成功！");
    }

    /**
     * 更新营销机会
     *
     * @param saleChance
     * @return
     */
    @RequiredPermission(code = "101004")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance){
        // 调用Service层的添加方法
        saleChanceService.updateSaleChance(saleChance);
        return success("营销机会数据更新成功！");
    }

    /**
     * 进入添加/修改营销机会数据页面
     *
     * @return
     */
    @RequestMapping("toSaleChancePage")
    public String toSaleChancePage(Integer saleChanceId,HttpServletRequest request){
        //判断saleChanceId是否为空
        if (saleChanceId != null){
            //通过id查询营销机会数据
            SaleChance saleChance = saleChanceService.selectByPrimaryKey(saleChanceId);
            //将数据设置到请求域中
            request.setAttribute("saleChance",saleChance);
        }

        return "saleChance/add_update";
    }

    /**
     * 删除营销机会
     *
     * @param ids
     * @return
     */
    @RequiredPermission(code = "101003")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids){
        //调用service层的删除方法
        saleChanceService.deleteSaleChance(ids);
        return success("营销机会数据删除成功！");
    }

    /**
     * 更新营销机会的开发状态
     *
     * @param id
     * @param devResult
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(Integer id, Integer devResult) {

        saleChanceService.updateSaleChanceDevResult(id, devResult);

        return success("开发状态更新成功！");
    }



}
