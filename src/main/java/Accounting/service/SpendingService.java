package Accounting.service;

import Accounting.entity.CategoryType;
import Accounting.entity.Income;
import Accounting.entity.Spending;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public interface SpendingService extends IService<Spending> {
    BigDecimal countSpendingMoney(Long userId);
    BigDecimal countSpendingYearMonthMoney(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId);  //按月统计总消费
    List<Spending> listYearMonth(@Param("year") String year, @Param(("month")) String month, @Param("userId") Long userId); //根据年、月进行查询
    List<Spending> spendingListYear(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId);  //根据年份进行查询
    List<CategoryType> SpendingCategory(@Param("year") String year, @Param(("month")) String month, @Param("userId") Long userId);  //按分类统计消费记录，进行分类
}
