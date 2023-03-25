package Accounting.service;

import Accounting.entity.SpendingType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpendingTypeService extends IService<SpendingType> {
    List<String> getSpendingTypes(Long userId);
}
