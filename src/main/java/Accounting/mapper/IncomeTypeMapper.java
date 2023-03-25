package Accounting.mapper;

import Accounting.entity.IncomeType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@MapperScan("Accounting/mapper/xml/IncomeTypeMapper.xml")
public interface IncomeTypeMapper extends BaseMapper<IncomeType> {
    List<String> getIncomeTypes(Long userId);
}
