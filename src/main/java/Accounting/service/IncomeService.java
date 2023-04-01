package Accounting.service;

import Accounting.entity.Income;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public interface IncomeService extends IService<Income> {
    BigDecimal countIncomeMoney(Long userId);
    List<Income> incomeListYearMonth(@Param("year") String year, @Param(("month")) String month,@Param("userId") Long userId); //根据年、月进行查询
    List<Income> incomeListYear(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId);  //根据年份进行查询
}
