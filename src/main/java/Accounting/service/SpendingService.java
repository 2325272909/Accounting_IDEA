package Accounting.service;

import Accounting.entity.Spending;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpendingService extends IService<Spending> {
}
