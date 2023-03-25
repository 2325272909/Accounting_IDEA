package Accounting.service.Impl;

import Accounting.entity.Income;
import Accounting.mapper.IncomeMapper;
import Accounting.service.IncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:IncomeImpl
 * @Description:收入记录 服务类
 * @Author:liumengying
 * @Date: 2023/3/17 16:34
 * Version v1.0
 */
@Service
@Transactional
public class IncomeImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {
}
