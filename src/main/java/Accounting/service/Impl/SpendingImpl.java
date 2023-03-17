package Accounting.service.Impl;

import Accounting.entity.Spending;
import Accounting.mapper.SpendingMapper;
import Accounting.service.SpendingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName:SpendingImpl
 * @Description:消费表的服务类
 * @Author:liumengying
 * @Date: 2023/3/17 11:18
 * Version v1.0
 */
@Service
public class SpendingImpl extends ServiceImpl<SpendingMapper, Spending> implements SpendingService {
}
