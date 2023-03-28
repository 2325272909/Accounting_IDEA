package Accounting.service;

import Accounting.entity.SpendingCredential;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpendingCredentialService extends IService<SpendingCredential> {
    List<String> getSpendingCredential(Long userId);
}
