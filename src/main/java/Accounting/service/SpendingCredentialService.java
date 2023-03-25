package Accounting.service;

import Accounting.entity.SpendingCredential;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpendingCredentialService extends IService<SpendingCredential> {
    List<String> getSpendingCredential(Long userId);
}
