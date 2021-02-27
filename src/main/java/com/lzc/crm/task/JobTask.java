package com.lzc.crm.task;

import com.lzc.crm.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 *
 */
@Component
public class JobTask {
    @Resource
    private CustomerService customerService;

    /**
     * 自动更新
     * @Scheduled(cron = "0/2 * * * * ?") //  两秒
     */
//    @Scheduled(cron = "0/2 * * * * ?") //  两秒
    @Scheduled(cron = "0 0 2 1 * ? ") //表示在每月的1日的凌晨2点调整任务
    public void job(){
        System.out.println("定时任务开始执行 --> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 调用需要被定时执行的方法
        customerService.updateCustomerState();
    }

}
