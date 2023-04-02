package Accounting.service.Impl;

import Accounting.entity.Income;
import Accounting.entity.Spending;
import Accounting.mapper.SpendingMapper;
import Accounting.service.SpendingService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName:SpendingImpl
 * @Description:消费表的服务类
 * @Author:liumengying
 * @Date: 2023/3/17 11:18
 * Version v1.0
 */
@Service
@Transactional
public class SpendingImpl extends ServiceImpl<SpendingMapper, Spending> implements SpendingService {
    @Override
    public BigDecimal countSpendingMoney(Long userId) {
        return baseMapper.countSpendingMoney(userId);
    }

    @Override
    public BigDecimal countSpendingYearMonthMoney(String year, String month, Long userId) {
        return baseMapper.countSpendingYearMonthMoney(year,month,userId);
    }

    @Override
    public List<Spending> listYearMonth(String year, String month, Long userId) {
        return baseMapper.listYearMonth(year,month,userId);
    }

    @Override
    public List<Spending> spendingListYear(String year, String month, Long userId) {
        return baseMapper.spendingListYear(year,month,userId);
    }
}
