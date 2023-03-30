package Accounting.mapper;

import Accounting.entity.SpendingType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpendingTypeMapper extends BaseMapper<SpendingType> {

    //根据用户id读取 消费类型 名称
    List<String> getSpendingTypes(Long userId);

    boolean deleteSpendingType(@Param("userId") Long userId, @Param("spendingTypeName") String spendingTypeName);
}
