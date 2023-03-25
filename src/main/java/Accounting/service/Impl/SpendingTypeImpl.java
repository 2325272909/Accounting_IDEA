package Accounting.service.Impl;

import Accounting.entity.SpendingType;
import Accounting.mapper.SpendingMapper;
import Accounting.mapper.SpendingTypeMapper;
import Accounting.service.SpendingTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName:SpendingTypeImpl
 * @Description:消费类型 类
 * @Author:liumengying
 * @Date: 2023/3/17 11:25
 * Version v1.0
 */

@Service
@Transactional
public class SpendingTypeImpl extends ServiceImpl<SpendingTypeMapper, SpendingType> implements SpendingTypeService {
    @Override
    public List<String> getSpendingTypes(Long userId) {
         List<String> lists = baseMapper.getSpendingTypes(userId);
         return lists;
    }
}
