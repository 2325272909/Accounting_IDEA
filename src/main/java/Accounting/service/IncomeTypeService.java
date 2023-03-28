package Accounting.service;

import Accounting.entity.IncomeType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IncomeTypeService extends IService<IncomeType> {
    List<String> getIncomeTypes(Long userId);

}
