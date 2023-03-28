package Accounting.controller;

import Accounting.common.R;
import Accounting.entity.Spending;
import Accounting.service.SpendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:SpendingController
 * @Description:消费记录模块-控制类
 * @Author:liumengying
 * @Date: 2023/3/17 19:40
 * Version v1.0
 */
@RestController
@RequestMapping("/spending")
@Slf4j
public class SpendingController {

    @Autowired
    private SpendingService spendingService;

    @PostMapping("/add")
    public R<String> add(@RequestBody Spending spending, HttpSession session){
      log.info("进入添加消费记录函数，spending:"+spending.toString());
      log.info("查看session:"+session.getAttribute("userId"));
        if(spendingService.save(spending)){
            return R.success("添加成功");
        }
        return R.error("添加失败");

    }
}
