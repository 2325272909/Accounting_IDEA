package Accounting.controller;

import Accounting.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:SpendingController
 * @Description:消费记录模块-控制类
 * @Author:liumengying
 * @Date: 2023/3/17 19:40
 * Version v1.0
 */
@RestController
@RequestMapping("/spending")
public class SpendingController {

    @Autowired
    private SpendingService spendingService;

}
