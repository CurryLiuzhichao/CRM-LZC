package com.lzc.crm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzc.crm.annoation.RequiredPermission;
import com.lzc.crm.base.BaseController;
import com.lzc.crm.base.ResultInfo;
import com.lzc.crm.dao.CustomerMapper;
import com.lzc.crm.query.CustomerQuery;
import com.lzc.crm.service.CustomerService;
import com.lzc.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    /**
     * 分页条件查询客户列表
     *
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomerByParams(customerQuery);
    }

    /**
     * 进入客户信息管理页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "customer/customer";
    }

    /**
     * 添加客户信息
     *
     * @param customer
     * @return
     */
    @RequiredPermission(code = "201001")
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return success("添加客户信息成功！");
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    @RequiredPermission(code = "201002")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return success("修改客户信息成功！");
    }

    /**
     * 删除客户信息
     *
     * @param id
     * @return
     */
    @RequiredPermission(code = "201003")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
        return success("删除客户信息成功！");
    }



    /**
     * 打开添加或修改客户信息的对话框
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("toAddOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer id, HttpServletRequest request) {
        // 如果id不为空，则查询客户记录
        if (null != id) {
            // 通过id查询客户记录
            Customer customer = customerService.selectByPrimaryKey(id);
            // 将客户记录存到作用域中
            request.setAttribute("customer",customer);
        }
        return "customer/add_update";
    }

    /**
     * 打开客户的订单页面
     *
     * @param customerId
     * @param model
     * @return
     */
    @RequestMapping("toCustomerOrderPage")
    public String toCustomerOrderPage(Integer customerId, Model model) {
        // 通过客户ID查询客户记录，设置到请求域中
        model.addAttribute("customer", customerService.selectByPrimaryKey(customerId));
        return "customer/customer_order";
    }


    /**
     * 客户贡献分析
     *
     * @param customerQuery
     * @return
     */
    @RequestMapping("queryCustomerContributionByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomerContributionByParams(customerQuery);
    }

    /**
     * 查询客户构成 （柱状图数据处理）
     *
     * @return
     */
    @RequestMapping("countCustomerMake")
    @ResponseBody
    public Map<String, Object> countCustomerMake() {
        return customerService.countCustomerMake();
    }

    /**
     * 查询客户构成 （饼状图数据处理）
     *
     * @return
     */
    @RequestMapping("countCustomerMake02")
    @ResponseBody
    public Map<String, Object> countCustomerMake02() {
        return customerService.countCustomerMake02();
    }

}
