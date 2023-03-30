package Accounting.service;

import Accounting.entity.SpendingType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpendingTypeService extends IService<SpendingType> {
    List<String> getSpendingTypes(Long userId);
    boolean deleteSpendingType(@Param("userId") Long userId, @Param("spendingTypeName") String spendingTypeName);
}
