package Accounting.controller;

import Accounting.common.R;
import Accounting.entity.CategoryType;
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
     */
    @GetMapping("/countSpendingYearMonthMoney")
    public R<BigDecimal> countSpendingYearMonthMoney(@RequestParam String year,@RequestParam String month,@RequestParam String userId){
//        Long userId=Long.valueOf(map.get("userId"));  //获取用户id
//        String year = map.get("year");  //年
//        String month = map.get("month");  //月
        log.info("进入按月统计金额函数:"+year+"年"+month+"月"+userId);
        Long id = Long.valueOf(userId);
        BigDecimal money = spendingService.countSpendingYearMonthMoney(year,month,id);
        log.info("money:"+money);
        if(money==null){
            return R.success(new BigDecimal(0));
        }
        return R.success(money);
    }

    /**
     * 按年、月查询消费记录
     * @return
     */
    @GetMapping("/listYearMonth")
    public R<List<Spending>> listYearMonth(@RequestParam String year,@RequestParam String month,@RequestParam String userId){
        log.info("进入消费按月展示函数，接收数据:"+year+"年"+month+"月"+userId );
        Long id=Long.valueOf(userId);  //获取用户id
        List<Spending> list = spendingService.listYearMonth(year,month,id);
        if(list.isEmpty()){
            return R.error("无数据");
        }
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


    /**
     * 分类统计消费记录，绘制饼图
     */
    @GetMapping("/countSpendingCategory")
    public R<List<CategoryType>> countSpendingCategory(@RequestParam String year,@RequestParam String month,@RequestParam Long userId){
        log.info("进入统计分类-消费函数，接收userId:"+userId);
        List<CategoryType> list = spendingService.SpendingCategory(year,month,userId);
        return R.success(list);
    }

    @DeleteMapping("delete_spending")
    public R<String> deleteSpending(@RequestBody Map<String,Long> bodyParams){
        Long spendingId =bodyParams.get("spendingId");
        log.info("进入删除消费记录函数,spendingId:"+spendingId);
        spendingService.removeById(spendingId);
        return R.success("删除成功");
    }

    @PostMapping("updateSpending")
    public R<String> updateSpending(@RequestBody Spending spending){
        log.info("进入修改消费记录函数:"+spending);
        spendingService.updateById(spending);
        return R.success("更新成功");
    }
}
