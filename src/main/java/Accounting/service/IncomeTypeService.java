package Accounting.service;

import Accounting.entity.IncomeType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IncomeTypeService extends IService<IncomeType> {
    List<String> getIncomeTypes(Long userId);

}
