package Accounting.mapper;

import Accounting.entity.Income;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface IncomeMapper extends BaseMapper<Income> {
}
