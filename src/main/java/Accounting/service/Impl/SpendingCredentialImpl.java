package Accounting.service.Impl;

import Accounting.entity.SpendingCredential;
import Accounting.mapper.SpendingCredentialMapper;
import Accounting.service.SpendingCredentialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName:SpendingCredentialImpl
 * @Description:消费凭据服务类
 * @Author:liumengying
 * @Date: 2023/3/17 15:48
 * Version v1.0
 */

@Service
@Transactional
public class SpendingCredentialImpl extends ServiceImpl<SpendingCredentialMapper, SpendingCredential>
        implements SpendingCredentialService {
    @Override
    public List<String> getSpendingCredential(Long userId) {
        List<String> lists = baseMapper.getSpendingCredential(userId);
        return lists;
    }

    @Override
    public boolean deleteSpendingCredential(Long userId, String spendingCredentialName) {
        return baseMapper.deleteSpendingCredential(userId,spendingCredentialName);
    }
}
