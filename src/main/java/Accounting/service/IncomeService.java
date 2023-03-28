package Accounting.service;

import Accounting.entity.Income;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IncomeService extends IService<Income> {
}
