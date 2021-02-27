package com.lzc.crm.controller;

import com.lzc.crm.base.BaseController;
import com.lzc.crm.query.CustomerOrderQuery;
import com.lzc.crm.service.CustomerOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("order")
@Controller
public class CustomerOrderController extends BaseController {

    @Resource
    private CustomerOrderService customerOrderService;

    /**
     * 分页多条件查询客户订单列表
     *
     * @param customerOrderQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery) {
        return customerOrderService.queryCustomerOrderByParams(customerOrderQuery);
    }

    /**
     * 打开订单详情的页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toOrderDetailPage")
    public String toOrderDetailPage(Integer orderId, Model model) {

        // 通过订单ID查询对应的订单记录
        Map<String,Object> map = customerOrderService.queryOrderById(orderId);
        // 将数据设置到请求域中
        model.addAttribute("order",map);

        return "customer/customer_order_detail";
    }


}
