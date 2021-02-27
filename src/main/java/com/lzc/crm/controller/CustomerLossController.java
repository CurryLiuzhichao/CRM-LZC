package com.lzc.crm.controller;

import com.lzc.crm.annoation.RequiredPermission;
import com.lzc.crm.base.BaseController;
import com.lzc.crm.base.ResultInfo;
import com.lzc.crm.query.CustomerLossQuery;
import com.lzc.crm.service.CustomerLossService;
import com.lzc.crm.vo.CustomerLoss;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("customer_loss")
@Controller
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    /**
     * 进入客户流失管理页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "customerLoss/customer_loss";
    }

    /**
     * 分页条件查询流失客户列表
     *
     * @param customerLossQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        return customerLossService.queryCustomerLossByParams(customerLossQuery);
    }

    /**
     * 打开添加暂缓/详情页面
     *
     * @param lossId
     * @return java.lang.String
     */
    @RequiredPermission(code = "202001")
    @RequestMapping("toCustomerLossPage")
    public String toCustomerLossPage(Integer lossId, Model model) {

        // 通过流失客户的ID查询对应流失客户的记录
        CustomerLoss customerLoss = customerLossService.selectByPrimaryKey(lossId);
        // 将流失客户对应的数据存到请求域中
        model.addAttribute("customerLoss", customerLoss);

        return "customerLoss/customer_rep";
    }

    /**
     *  更新流失客户的流失状态
     *
     * @param id
     * @param lossReason
     * @return
     */
    @PostMapping("updateCustomerLossStateById")
    @ResponseBody
    public ResultInfo updateCustomerLossStateById(Integer id, String lossReason) {
        customerLossService.updateCustomerLossStateById(id, lossReason);
        return success("确认流失成功！");
    }

}
