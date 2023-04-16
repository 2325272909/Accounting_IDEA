package Accounting.mapper;

import Accounting.entity.CategoryType;
import Accounting.entity.Income;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface IncomeMapper extends BaseMapper<Income> {
    BigDecimal countIncomeMoney(Long userId);  //统计总收入

    BigDecimal countIncomeYearMonthMoney(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId);  //按月统计总收入

    List<Income> incomeListYearMonth(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId); //根据年、月进行查询

    List<Income> incomeListYear(@Param("year") String year,@Param(("month")) String month,@Param("userId") Long userId);  //根据年份进行查询

    List<CategoryType> IncomeCategory(@Param("year") String year, @Param(("month")) String month, @Param("userId") Long userId);  //按分类统计收入记录，进行分类
}
