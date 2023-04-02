package Accounting.controller;

import Accounting.common.R;
import Accounting.entity.Income;
import Accounting.entity.Spending;
import Accounting.service.SpendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加消费记录
     * @param spending
     * @param session
     * @return
     */
    @PostMapping("/add")
    public R<String> add(@RequestBody Spending spending, HttpSession session){
      log.info("进入添加消费记录函数，spending:"+spending.toString());
      log.info("查看session:"+session.getAttribute("userId"));
        if(spendingService.save(spending)){
            return R.success("添加成功");
        }
        return R.error("添加失败");

    }


    /**
     * 计算总消费金额
     */
    @GetMapping("/countSpendingMoney")
    public R<BigDecimal> countMoney(@RequestParam String userId){
        Long id = Long.valueOf(userId);
        return R.success(spendingService.countSpendingMoney(id));
    }

    /**
     * 按月统计消费金额
     * @param map
     * @return
     */
    @GetMapping("/countSpendingYearMonthMoney")
    public R<BigDecimal> countSpendingYearMonthMoney(@RequestBody Map<String,String> map){
        log.info("进入消费按月统计函数，接收数据map:"+map);
        Long userId=Long.valueOf(map.get("userId"));  //获取用户id
        String year = map.get("year");  //年
        String month = map.get("month");  //月
        BigDecimal money = spendingService.countSpendingYearMonthMoney(year,month,userId);
        return R.success(money);
    }

    /**
     * 按年、月查询消费记录
     * @param map
     * @return
     */
    @GetMapping("/listYearMonth")
    public R<List<Spending>> listYearMonth(@RequestBody Map<String,String> map){
        log.info("进入消费按月展示函数，接收数据map:"+map);
        Long userId=Long.valueOf(map.get("userId"));  //获取用户id
        String year = map.get("year");  //年
        String month = map.get("month");  //月
        List<Spending> list = spendingService.listYearMonth(year,month,userId);
        return R.success(list);
    }
    /**
     * 按年查询消费记录
     * @param map
     * @return
     */
    @GetMapping("/listYear")
    public R<List<Spending>> listYear(@RequestBody Map<String,String> map){
        log.info("进入消费按年展示函数，接收数据map:"+map);
        Long userId=Long.valueOf(map.get("userId"));  //获取用户id
        String year = map.get("year");  //年
        String month = map.get("month");  //月
        List<Spending> list = spendingService.spendingListYear(year,month,userId);
        return R.success(list);
    }

}
