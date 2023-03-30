package Accounting.controller;

import Accounting.common.R;
import Accounting.entity.Income;
import Accounting.entity.Spending;
import Accounting.service.IncomeService;
import Accounting.service.IncomeTypeService;
import Accounting.service.SpendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName:IncomeController
 * @Description:收入 功能控制器
 * @Author:liumengying
 * @Date: 2023/3/28 20:18
 * Version v1.0
 */
@RestController
@RequestMapping("/income")
@Slf4j
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private IncomeTypeService incomeTypeService;

    /**
     * 添加收入
     * @param income
     * @return
     */
    @PostMapping("/add")
    public R<String> add(@RequestBody Income income){
        log.info("进入添加收入记录函数，income:"+income.toString());
        if(incomeService.save(income)){
            return R.success("添加成功");
        }
        return R.error("添加失败");

    }


    /**
     * 收入类型列表
     * @param userid
     * @return
     */
    @GetMapping("/typeList")
    public R<List<String>> list(@RequestParam String userid){
        log.info("进入添加收入记录函数，获取的userid:"+userid);
        Long userId = Long.valueOf(userid);
        return R.success(incomeTypeService.getIncomeTypes(userId));  //返回类型列表
    }

}
