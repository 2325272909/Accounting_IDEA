package Accounting.mapper;

import Accounting.entity.Spending;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpendingMapper extends BaseMapper<Spending> {
}
