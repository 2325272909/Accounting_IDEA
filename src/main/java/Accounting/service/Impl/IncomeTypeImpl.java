package Accounting.service.Impl;

import Accounting.entity.IncomeType;
import Accounting.mapper.IncomeMapper;
import Accounting.mapper.IncomeTypeMapper;
import Accounting.service.IncomeTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName:IncomeTypeImpl
 * @Description:收入类型 服务类
 * @Author:liumengying
 * @Date: 2023/3/17 17:34
 * Version v1.0
 */
@Service
public class IncomeTypeImpl extends ServiceImpl<IncomeTypeMapper, IncomeType> implements IncomeTypeService {
}
